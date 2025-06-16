package javabean;

import java.beans.PropertyChangeListener;

/**
 * 查看 https://docs.oracle.com/javase/tutorial/javabeans/writing/properties.html
 * 获得更新内容，里面定义了其它的属性。
 *
 * 只需一个监听器，就能收到PropertyChangeSupport.fire的所有的事件通知。
 * @Description
 * @Author hjg
 * @Date 2025-06-16 22:25
 */
public class BoundProperty {
    public static void main(String[] args) {
        Car car = new Car();

        PropertyChangeListener pcl = evt -> {
            System.out.println(evt.getPropertyName());
            System.out.println(evt.getOldValue());
            System.out.println(evt.getNewValue());
        };

        car.addPropertyChangeListener(pcl);

        //调用setter
        car.setName("Ford");

        System.out.println();

        car.setColor("Blue");
    }
}
