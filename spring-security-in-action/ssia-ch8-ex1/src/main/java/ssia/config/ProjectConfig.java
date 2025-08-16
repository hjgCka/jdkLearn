package ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
                        .roles("ADMIN")
                        .build()
        );

        uds.createUser(
                User.withUsername("bill")
                        .password("12345")
                        .roles("MANAGER")
                        .build()
        );

        return uds;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(
                c -> c.requestMatchers("/hello").hasRole("ADMIN")
                        .requestMatchers("/ciao").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/a").authenticated()
                        .requestMatchers(HttpMethod.POST, "/a").permitAll()

                        //支持ant风格的路径表达式
                        .requestMatchers("/a/b/**").authenticated()

                        //支持路径变量的正则表达式校验
                        .requestMatchers("/product/{code:^[0-9]*$}").authenticated()

                        //permitAll方法，如果提供了错误的用户名或密码，依然会认证错误导致无法访问
                        //还可用authenticated()要求都进行认证，denyAll()都拒绝访问
                        .anyRequest().permitAll()
        );

        http.csrf(c -> c.disable());

        return http.build();
    }
}
