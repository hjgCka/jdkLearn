package ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

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
                        .authorities("read", "write", "delete")
                        .build()
        );

        // 背后还是将角色名称，加上ROLE_前缀，设置为权限。
        // 角色与权限的管理，即通过用户获取角色，再通过角色获取权限。这需要开发者自己设计。
        var user1 = User.withUsername("jack")
                .password("12345")
                .roles("ADMIN")
                .build();

        var user2 = User.withUsername("jane")
                .password("12345")
                .roles("MANAGER")
                .build();

        uds.createUser(user1);
        uds.createUser(user2);

        return uds;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        /*String expression = """
                                hasAuthority('read') && ! hasAuthority('delete')
                            """;*/
        //spEL可以与权限无关
        String expression = "T(java.time.LocalTime).now().isAfter(T(java.time.LocalTime).of(12, 0))";

        http.authorizeHttpRequests(
                // permitAll，任何请求都不需要认证了
                //c -> c.anyRequest().permitAll()
                //c -> c.anyRequest().hasAuthority("read")
                //c -> c.anyRequest().hasAnyAuthority("read", "write")

                //可以通过角色来判断
                c -> c.anyRequest().hasRole("ADMIN")

                //只有当hasAuthority和hasAnyAuthority不满足时，才用access方法
                //c -> c.anyRequest().access(new WebExpressionAuthorizationManager(expression))
        );

        return http.build();
    }
}
