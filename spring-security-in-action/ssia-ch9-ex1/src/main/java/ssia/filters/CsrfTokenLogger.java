package ssia.filters;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-17 0:32
 */
public class CsrfTokenLogger implements Filter {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CsrfTokenLogger.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        log.info("CSRF Token: {}", csrfToken.getToken());

        chain.doFilter(request, response);
    }
}
