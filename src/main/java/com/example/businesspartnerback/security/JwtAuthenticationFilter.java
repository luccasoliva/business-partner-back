package com.example.businesspartnerback.security;

import com.example.businesspartnerback.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

     @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        AuthCredentials cred = new AuthCredentials();
        try {
            cred = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException ignored) {}
        UsernamePasswordAuthenticationToken usernamePat = new UsernamePasswordAuthenticationToken(cred.getEmail(),
                cred.getPassword(),
                Collections.emptyList());
        return getAuthenticationManager().authenticate(usernamePat);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = JwtUtils.createToken(userDetails.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        Map<String,String> tokens = new HashMap<>();
        tokens.put("accessToken", token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(401);
        response.setContentType("aplication/json");
        response.getWriter().append(json());
    }

    private CharSequence json() {
        long date = new Date().getTime();
        return "{"
                + "\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/api/v1/owner/login\"}";
    }
}
