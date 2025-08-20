package com.hjg.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-13 16:11
 */
@Configuration
public class ProjectConfig {

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
        http.cors(c -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration configuration = new CorsConfiguration();
                //Access-Control-Allow-Origin 头的值是包含 协议名称的
                configuration.setAllowedOrigins(List.of("http://example.com", "http://example.org", "http://127.0.0.1:8080", "http://localhost:8080"));
                configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                configuration.setAllowedHeaders(List.of("*"));
                return configuration;
            };

            c.configurationSource(source);
        });

        http.csrf(c -> c.disable());

        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();
    }
}
