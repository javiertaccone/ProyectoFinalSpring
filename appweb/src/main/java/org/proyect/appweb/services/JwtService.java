package org.proyect.appweb.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

@Service
public class JwtService {

    public String getJwtToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "accessToken");
        return (cookie != null) ? cookie.getValue() : null;
    }

    public HttpHeaders createHeadersWithJwt(HttpServletRequest request) {
        String jwtToken = getJwtToken(request);
        HttpHeaders headers = new HttpHeaders();
        if (jwtToken != null) {
            headers.setBearerAuth(jwtToken);
        }
        return headers;
    }
}
