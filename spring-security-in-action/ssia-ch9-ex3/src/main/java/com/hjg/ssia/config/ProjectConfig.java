package com.hjg.ssia.config;

import com.hjg.ssia.csrf.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-13 16:11
 */
@Configuration
public class ProjectConfig {

    private final CustomCsrfTokenRepository customCsrfTokenRepository;

    public ProjectConfig(CustomCsrfTokenRepository customCsrfTokenRepository) {
        this.customCsrfTokenRepository = customCsrfTokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();

        uds.createUser(
                User.withUsername("john")
                        .password("12345")
                        .authorities("read")
                        .build()
        );

        uds.createUser(
                User.withUsername("bill")
                        .password("12345")
                        .authorities("write")
                        .build()
        );

        return uds;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.csrfTokenRepository(customCsrfTokenRepository);
            c.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());
        });

        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();
    }
}
