package com.hjg.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 如果不自己提供一个UserDetailsService这样的bean，spring会自行配置UserDetailsService，并生成user及其密码。
 * 密码会打印到控制台。
 * @Description
 * @Author hjg
 * @Date 2025-08-07 0:46
 */
@Profile("dev1")
@Configuration
public class ProjectConfig {

    /*@Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 默认保护所有的endPoint，但是有些是不需要保护的。配置该bean更改默认配置。
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        //配置认证流程，httpBasic方法使用http basic认证
        http.httpBasic(Customizer.withDefaults());

        //在端点级别配置 授权 规则，可以关闭特定端点
        http.authorizeHttpRequests(
                //所有请求都需被认证
                c -> c.anyRequest().authenticated()

                //全部放行
                //c -> c.anyRequest().permitAll()
        );

        //另一种配置方法，不过这种配置依然打印密码到控制台，john和user都可以登录
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        var userDetailsService = new InMemoryUserDetailsManager(user);
        http.userDetailsService(userDetailsService);

        return http.build();
    }
}
