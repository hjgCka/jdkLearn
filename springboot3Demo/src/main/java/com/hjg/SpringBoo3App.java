package com.hjg;

import org.springframework.boot.Banner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Description
 * @Author hjg
 * @Date 2025-03-31 17:09
 */
@SpringBootApplication
public class SpringBoo3App {

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 42;
    }

    public static void main(String[] args) {
        //SpringApplication.run(SpringBoo3App.class, args);

        //可以换这种方式启动，这是可以设置SpringApplication的一些属性
        SpringApplication application = new SpringApplication(SpringBoo3App.class);
        application.setBannerMode(Banner.Mode.OFF);
        ApplicationContext applicationContext = application.run(args);

        //System.exit(SpringApplication.exit(applicationContext));
    }

}
