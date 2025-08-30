package advanced.javabean;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-30 20:09
 */
public class ConstrainedPropertyMain {
    public static void main(String[] args) {
        Car car = new Car();

        VetoableChangeListener vcl = evt -> {
            Object source = evt.getSource();
            System.out.println(source);

            System.out.println(evt.getOldValue());
            System.out.println(evt.getNewValue());
            System.out.println(evt.getPropertyName());

            if ("name".equals(evt.getPropertyName()) && "jack".equals(evt.getNewValue())) {
                throw new PropertyVetoException("名字不可为jack", evt);
            }
            if ("color".equals(evt.getPropertyName()) && "blue".equals(evt.getNewValue())) {
                throw new PropertyVetoException("颜色不可为蓝色", evt);
            }
        };

        car.addVetoableChangeListener(vcl);

        //调用setter
        try {
            car.setName("Ford");

            System.out.println("************************");

            car.setColor("blue");
        } catch (Exception e) {
            System.out.println(e);
        }

        car.removeVetoableChangeListener(vcl);
    }
}
