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
