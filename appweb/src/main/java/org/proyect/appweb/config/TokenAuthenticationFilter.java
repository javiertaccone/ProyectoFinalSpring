package org.proyect.appweb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.proyect.appweb.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RolService rolService;

    @Autowired
    private RestUserConfig restUserConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String rol = "";

        if (authentication != null){
            String username = authentication.getName();
            rol = rolService.getRolByUsername(username);
        } else {
            System.out.println("authentication is null");
        }

        String accessToken = authenticateAndGetToken(rol);

        System.out.println("Access token "  + accessToken);

        if (accessToken != null) {
            Cookie cookie = new Cookie("accessToken", accessToken);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        filterChain.doFilter(request, response);
    }
//ver que pasa al volver de rest




    private String authenticateAndGetToken(String rol) {


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode credentials = objectMapper.createObjectNode();
            credentials.put("username", restUserConfig.getUsernameByRole(rol));
            credentials.put("password", restUserConfig.getPasswordByRole(rol));

            System.out.println("username:  " + restUserConfig.getUsernameByRole(rol)+
                    "password:  " + restUserConfig.getPasswordByRole(rol));



            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String credentialsJson = objectMapper.writeValueAsString(credentials);

            System.out.println("Credentials JSON: " + credentialsJson);

            HttpEntity<String> request = new HttpEntity<>(credentials.toString(), headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    "http://localhost:8081/ratings/auth",
                    HttpMethod.POST,
                    request,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                ObjectNode responseBody = objectMapper.readValue(response.getBody(), ObjectNode.class);
                return responseBody.get("accessToken").asText();
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }
}
