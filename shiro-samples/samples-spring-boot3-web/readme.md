### 默认页面
启动项目后，直接访问localhost:8080时，由于HelloController，会访问hello.html页面。


### 登录页面
hello.html页面可以访问/login.html，访问时这个页面被authc保护。
FormAuthenticationFilter的onAccessDenied方法被执行，由于是GET请求，因此login.html页面会被允许访问。

接着通过LoginController，返回了login逻辑视图名称，将其解析为login.html页面。

### 登录
login.html页面的form没有写action属性，默认提交到当前页面，也就是http://localhost:8080/login.html。

提交到/login.html，这个路径由authc所保护。且form是POST提交的，里面有username和password这2个field，因此authc会在其onAccessDenied方法中执行登录。

登录成功后，又回到了最初的http://localhost:8080地址。

未配置的url是允许匿名访问的。

### 其他
如果idea启动不符合预期时，可以考虑用mvn启动，或者打包成jar启动。
