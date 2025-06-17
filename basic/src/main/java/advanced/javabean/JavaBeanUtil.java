package advanced.javabean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * 参考java bean规范的，第八章 Introspector
 * @Description
 * @Author hjg
 * @Date 2025-06-16 22:33
 */
public class JavaBeanUtil {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Car.class);

        PropertyDescriptor[] pdArray = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pdArray) {
            System.out.println(pd.getName());
            //还可以获取readMethod writeMethod
        }

        PropertyDescriptor pd = new PropertyDescriptor("color", Car.class);
        //可以进行setter getter操作，但是需要一个Car实例。
    }
}
