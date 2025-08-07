package com.hjg.ssia.config;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-07 8:06
 */
@Profile("dev2")
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //自定义认证逻辑，与默认配置不同，这里没有用到UserDetailsService和PasswordEncoder

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ("john".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Error!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //认证实现的类型，authentication是UsernamePasswordAuthenticationToken的子类才返回true
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
