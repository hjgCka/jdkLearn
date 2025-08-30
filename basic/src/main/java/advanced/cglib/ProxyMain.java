package advanced.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 将JDK调整到JDK8，能够正常运行。JDK17无法直接正常运行。
 * 目前cglib 最新版本是3.3.0，于2019年发布。
 * 截止到2025-06一直没发布过新版本，处于无人维护状态。
 * 现代的字节码生成工具，可用 ByteBuddy。Springboot3也是用的它。
 *
 * cglib实现方式是，动态生成要代理类的子类，并将其实例化，作为代理实例。
 * 所以可以代理接口、抽象方法、普通类。
 * 编程部分主要几种在各种callback的实现上。
 *
 * @Description
 * @Author hjg
 * @Date 2025-06-17 12:40
 */
public class ProxyMain {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        // 这个callback是MethodInterceptor
        enhancer.setCallback(new MethodInterceptor() {

            /**
             * @param obj    "this", the enhanced object
             * @param method intercepted Method
             * @param args   argument array; primitive types are wrapped
             * @param proxy  used to invoke super (non-intercepted method); may be called
             *               as many times as needed
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Class cls = method.getDeclaringClass();
                if (cls != Object.class && method.getReturnType().equals(String.class)) {
                    System.out.println("调用：" + method.getName());
                    return "hello world";
                }
                if (cls != Object.class && method.getReturnType().equals(Integer.class)) {
                    System.out.println("调用：" + method.getName());
                    return 666;
                }

                //调用父类的方法
                return proxy.invokeSuper(obj, args);
            }
        });

        PersonService personService = (PersonService) enhancer.create();
        System.out.println(personService.getAge());
    }
}
