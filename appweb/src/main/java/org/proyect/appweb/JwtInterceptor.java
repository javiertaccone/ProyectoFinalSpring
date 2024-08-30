package org.proyect.appweb;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class JwtInterceptor implements ClientHttpRequestInterceptor {


    private String jwtToken;

    public JwtInterceptor(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (jwtToken != null && !jwtToken.isEmpty()) {
            request.getHeaders().setBearerAuth(jwtToken);
        }
        return execution.execute(request, body);
    }
}
