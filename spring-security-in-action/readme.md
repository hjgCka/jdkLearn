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
