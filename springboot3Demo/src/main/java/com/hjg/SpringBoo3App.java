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

    /**
     * 由于配置了webMvc的资源处理器，因此可以用如下路径，访问类路径下的html
     * http://localhost:8088/ws-client/wsClient.html
     * 即使打包成jar来启动，也是可以这样访问。
     * @param args
     */
    public static void main(String[] args) {
        //SpringApplication.run(SpringBoo3App.class, args);

        // 使用devtools的情况下，打印2次，一次为main，另一个为restart
        System.out.println("classLoaderName: " + SpringBoo3App.class.getClassLoader().toString()
                + ", thread = " + Thread.currentThread().getName());

        //可以换这种方式启动，这是可以设置SpringApplication的一些属性
        SpringApplication application = new SpringApplication(SpringBoo3App.class);
        application.setBannerMode(Banner.Mode.OFF);
        // application.run是一个阻塞方法，JVM关闭前不会执行下面的语句
        ApplicationContext applicationContext = application.run(args);

        //System.exit(SpringApplication.exit(applicationContext));
    }

}
