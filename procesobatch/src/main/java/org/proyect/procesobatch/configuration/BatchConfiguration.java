package org.proyect.procesobatch.configuration;

import org.proyect.procesobatch.DTO.FilmDTO;
import org.proyect.procesobatch.domain.FilmExport;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Autowired
    @Lazy
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public JdbcCursorItemReader<FilmDTO> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<FilmDTO>()
                .name("filmReader")
                .dataSource(dataSource)
                .sql("SELECT id, title, release_year FROM films WHERE id NOT IN (SELECT film_id FROM film_exports)")
                .rowMapper((rs, rowNum) -> new FilmDTO(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getInt("release_year")
                ))
                .build();
    }

    @Bean
    public FlatFileItemWriter<FilmDTO> csvWriter() {
        return new FlatFileItemWriterBuilder<FilmDTO>()
                .name("filmCsvWriter")
                .resource(new FileSystemResource("filmsExports.csv"))
                .delimited()
                .delimiter(",")
                .names("id", "title", "releaseYear")
                .headerCallback(writer -> writer.write("filmId,title,releaseYear"))
                .append(true)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<FilmExport> dbWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<FilmExport>()
                .dataSource(dataSource)
                .sql("INSERT INTO film_exports (job_id, film_id, exported_at) VALUES (:jobId, :filmId, :exportedAt)")
                .beanMapped()
                .build();
    }

    @Bean
    public ItemWriter<FilmDTO> filmExportItemWriter(JdbcBatchItemWriter<FilmExport> dbWriter) {
        return chunk -> {
            List<FilmDTO> items = (List<FilmDTO>) chunk.getItems();
            List<FilmExport> exports = items.stream()
                    .map(film -> new FilmExport(
                            1L,  // Ajusta el jobId según sea necesario
                            film.getId(),
                            LocalDateTime.now()
                    ))
                    .collect(Collectors.toList());
            dbWriter.write((Chunk<? extends FilmExport>) exports);
        };
    }

    @Bean
    public CompositeItemWriter<FilmDTO> compositeWriter(FlatFileItemWriter<FilmDTO> csvWriter, ItemWriter<FilmDTO> filmExportItemWriter) {
        return new CompositeItemWriterBuilder<FilmDTO>()
                .delegates(csvWriter, filmExportItemWriter)
                .build();
    }

    @Bean
    public Step exportFilmStep() {
        return new StepBuilder("exportFilmStep", jobRepository)
                .<FilmDTO, FilmDTO>chunk(10, transactionManager)
                .reader(reader(dataSource()))
                .writer(compositeWriter(csvWriter(), filmExportItemWriter(dbWriter(dataSource()))))
                .build();
    }

    @Bean
    public Job exportFilmJob() {
        return new JobBuilder("exportFilmJob", jobRepository)
                .start(exportFilmStep())
                .build();
    }
}
