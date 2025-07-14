package org.apache.shiro.samples;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS 过滤器（最灵活）
 * @Description
 * @Author hjg
 * @Date 2025-07-11 18:45
 */
@Controller
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // 允许凭证
        config.addAllowedOriginPattern("*"); // 允许所有源
        config.addAllowedHeader("*");    // 允许所有请求头
        config.addAllowedMethod("*");    // 允许所有方法（GET/POST等）
        config.setMaxAge(3600L);        // 预检缓存时间

        source.registerCorsConfiguration("/**", config); // 对所有路径生效
        return new CorsFilter(source);
    }

}
