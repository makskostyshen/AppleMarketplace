package com.example.applemarketplace.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtSecurityFilter extends OncePerRequestFilter {
    private final JwtParser jwtParser;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {

        getBearerToken(request)
                .ifPresent(token -> {
                    try {
                        String username = jwtParser.getUsername(token);

                        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                            setAuthentication(username, token);
                        }
                    } catch (Exception e) {
                        log.error("Jwt token {} cannot be parsed", token, e);
                    }
                });

        filterChain.doFilter(request, response);
    }


    Optional<String> getBearerToken(final HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return Optional.of(authHeader.substring(7));
        }
        return Optional.empty();
    }

    private void setAuthentication(final String username, final String token) {
        SecurityContextHolder.getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                jwtParser.getRoles(token)
                        )
                );
    }
}
