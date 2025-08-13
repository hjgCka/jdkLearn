package com.hjg.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
}
