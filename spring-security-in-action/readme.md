### ssia-ch2-ex1
演示了如何配置PasswordEncoder 和 UserDetailsService，以及通过配置SecurityFilterChain来覆盖自定义配置。

SecurityFilterChain提供HttpSecurity http参数，http.httpBasic方法将BasicAuthenticationFilter放入了过滤器链。

同时实现了一个自定义的AuthenticationProvider，可以用这个自定义的类实现自定义认证流程。

### ssia-ch3-ex1
演示如何应用自定义的UserDetailsService 和 UserDetails 接口实现。

### ssia-ch3-ex2
演示自定义的UserDetailsService接口实现，这个实现使用了spring security提供的JdbcUserDetailsManager。
它用来获取用户，但是没演示修改、删除用户的功能。

使用h2数据库，以及脚本文件，通过数据库的用户记录进行登录。

### ssia-ch5-ex1
添加自定义过滤器，在特定过滤器的前面 或 后面。

可以扩展spring security提供的过滤器，也可以自己实现Filter接口。

### ssia-ch5-ex2
在过滤器链的同一个位置添加多个过滤器。不会发生替代这个行为。
此时的行为是未定义的。但是最好不要这样做。

你需要知道全部的过滤器。

可以禁止自行创建一个user并打印密码。

关于过滤器链的顺序是固定的，但是开发者可以决定在某个过滤器的前面或者后面，或者某个过滤器的位置，放置过滤器。
它的顺序定义在SecurityWebFiltersOrder。

### ssia-ch6-ex1
通过实现AuthenticationProvider接口，实现自定义的认证逻辑。

认证完成之后，会存储Authentication实例，为之后的请求可以访问。
保存Authentication实例的对象被称为SecurityContext。

SecurityContext本身又有3种模型进行管理。

springboot管理的对象的线程池，比如使用@Async注解的方法，spring知道这个线程池。
当使用它时，spring security自动将securitycontext复制到执行线程。

### ssia-ch6-ex2
使用@Async，以及自定义线程时，通过线程的包装类 或者 线程池的包装类，复制security context。

### ssia-ch6-ex3
定义在basic认证失败时，所执行的特定逻辑和返回的值。

### ssia-ch6-ex4
定义在form-based登录时，登陆成功和登录失败时执行自定义逻辑。

可同时支持formLogin 和 httpBasic。
