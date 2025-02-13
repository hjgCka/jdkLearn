package enums;

/**
 * @Description
 * @Author hjg
 * @Date 2025-02-06 14:06
 */
public class HelloInt2 implements HelloInt {
    @Override
    public void sayHi(RunInt runInt) {
        System.out.println("hello");
        runInt.sayRun();
    }
}
