package basic;

/**
 * @Description
 * @Author hjg
 * @Date 2025-03-24 17:05
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "hello";

        //UTF - 16 是 Java 中 String 类使用的编码方式，
        // 而 Unicode 编码单元就是 UTF - 16 编码里的基本存储单位。
        //utf-16的编码单元是16位（2个字节），所以BMP内就是用一个编码单元就可以表示，而之外要用2个编码单元
        s.length();
        s.codePoints().forEach(c -> System.out.println(c));
        s.intern();
    }
}
