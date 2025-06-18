package advanced.delegation;

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
                //UserService.class.getClassLoader(),
                //UserService.class.getInterfaces(),
                /**
                 * userService.getClass().getInterfaces()返回[UserService.class]（即实现类直接实现的接口）。
                 * 动态代理生成的类会实现UserService接口，可以安全转换为UserService。
                 */
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new LogginHandler(userService)
        );

        /**
         * 生成的UserService代理类实例，与实现InvocationHandler接口的类，有什么关系？
         * 生成的类，实现了代理的接口，并且继承了Proxy类。
         * 但是其实现，应该是调用InvocationHandler接口的invoke方法来实现。所以能够拦截到每个方法。
         */
        proxy.saveUser();
    }
}
