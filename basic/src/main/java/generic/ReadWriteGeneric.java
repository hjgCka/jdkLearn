package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author hjg
 * @Date 2025-01-25 9:58
 */
public class ReadWriteGeneric {

    /**
     * 读取出来的对象只是Fruit对象，即使实际类型是Apple。如果需要Apple对象，需要类型强制转换。
     * @param list
     * @return
     */
    private static Fruit readFirstFruit(List<? extends Fruit> list) {
        return list.get(0);
    }

    /**
     * 将Fruit或者其子类的对象，放入list
     * @param list  Fruit类型 或者 其子类类型
     */
    private static void writeFruits(List<? super Fruit> list, Fruit... fruits) {
        for (Fruit fruit : fruits) {
            list.add(fruit);
        }
    }

    public static void main(String[] args) {
        List<Fruit> fruitList = new ArrayList<>();
        writeFruits(fruitList, new Fruit(), new Apple(), new XinjiangApple());

        /**
         * 不能如下这样使用
         * List<Apple> appleList = new ArrayList<>();
         * writeFruits(appleList, new Apple());
         */

        /**
         * 但是readFirstFruit方法，支持各种类型的读取，但是不能进行其它方法的调用。
         */
        System.out.println(readFirstFruit(fruitList));

        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple());
        System.out.println(readFirstFruit(appleList));
    }
}
