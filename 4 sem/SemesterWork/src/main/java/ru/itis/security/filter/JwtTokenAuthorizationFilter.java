package ru.itis.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.Collections;

@RequiredArgsConstructor
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
        } else {
            String tokenHeader = request.getHeader("Authorization");

            if (tokenHeader == null) {
                logger.warn("Token is missing");
                filterChain.doFilter(request, response);
            } else if (tokenHeader.startsWith("Bearer ")) {
                String token = tokenHeader.substring("Bearer ".length());

                try {
                    DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                            .build().verify(token);

                    if (decodedJWT.getClaim("expiredAt").asDate().after(Date.from(Instant.now()))){
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(token, null,
                                        Collections.singletonList(
                                                new SimpleGrantedAuthority(decodedJWT.getClaim("role").asString())));

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(request, response);
                    } else {
                        logger.warn("Token expired");
                        sendForbidden(response);
                    }

                } catch (JWTVerificationException e) {
                    sendForbidden(response);
                }
            } else {
                logger.warn("Wrong token format");
                sendForbidden(response);
            }
        }
    }

    private void sendForbidden(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        objectMapper.writeValue(response.getWriter(), Collections.singletonMap("error", "user not found with token"));
    }
}
