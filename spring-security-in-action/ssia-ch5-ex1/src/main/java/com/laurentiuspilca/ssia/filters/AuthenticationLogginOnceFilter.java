package com.laurentiuspilca.ssia.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-08 18:43
 */
public class AuthenticationLogginOnceFilter extends OncePerRequestFilter {

    private final Logger logger =
            LoggerFactory.getLogger(AuthenticationLogginOnceFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = request.getHeader("Request-Id");
        logger.info("AuthenticationLogginOnceFilter Successfully authenticated request with id {}", requestId);
        filterChain.doFilter(request, response);
    }
}
