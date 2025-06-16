package delegation;

import java.lang.reflect.Proxy;

/**
 * 动态代理是spring aop的基础。
 * Spring AOP 的默认策略：
 * 目标对象实现了接口 → 使用 JDK 动态代理
 * 目标对象未实现接口 → 使用 CGLIB
 * @Description
 * @Author hjg
 * @Date 2025-06-16 15:48
 */
public class DelegationMain {
    public static void main(String[] args) {
        //实际调用类
        UserService userService = new UserServiceImpl();

        //动态生成的代理类
        UserService proxy = (UserService) Proxy.newProxyInstance(
                UserService.class.getClassLoader(),
                UserService.class.getInterfaces(),
                new LogginHandler(userService)
        );

        proxy.saveUser();
    }
}
