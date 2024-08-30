package org.proyect.serviciorest.services.implementations;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.proyect.serviciorest.config.JwtConfigurationProperties;
import org.proyect.serviciorest.services.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtConfigurationProperties jwtConfigurationProperties;
    private  final NimbusJwtEncoder nimbusJwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Override
    public Jwt generateToken(UserDetails userDetails) {
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).type("JWT").build();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(userDetails.getUsername())
                .expiresAt(Instant.now().plusMillis(jwtConfigurationProperties.duration().toMillis()))
                .claim("authorities",
                    userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                )
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader, jwtClaimsSet );

        return nimbusJwtEncoder.encode(jwtEncoderParameters);
    }

}
