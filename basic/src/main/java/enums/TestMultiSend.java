package enums;

/**
 * @Description
 * @Author hjg
 * @Date 2025-02-06 14:10
 */
public class TestMultiSend {

    public static void main(String[] args) {
        HelloInt t1 = new HelloInt1();
        HelloInt t2 = new HelloInt2();

        RunInt r1 = new RunInt1();
        RunInt r2 = new RunInt2();

        t1.sayHi(r1);
        t1.sayHi(r2);
    }
}
