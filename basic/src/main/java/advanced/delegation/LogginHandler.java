package advanced.delegation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author hjg
 * @Date 2025-06-16 15:49
 */
public class LogginHandler implements InvocationHandler {

    private Object target;

    public LogginHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用方法：" + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("调用方法完成" + method.getName());
        return result;
    }
}
