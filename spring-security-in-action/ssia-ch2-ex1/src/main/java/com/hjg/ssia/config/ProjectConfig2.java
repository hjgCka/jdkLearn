package com.hjg.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 使用这个配置时，由于没有用到UserDetailsService，所以spring也不会自动生成这个实例。
 * 这样控制台就没有打印密码字符串和一个默认用户：user。
 * @Description
 * @Author hjg
 * @Date 2025-08-07 8:23
 */
@Profile("dev2")
@Configuration
public class ProjectConfig2 {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    public ProjectConfig2(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

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

        http.authenticationProvider(customAuthenticationProvider);

        return http.build();
    }
}
