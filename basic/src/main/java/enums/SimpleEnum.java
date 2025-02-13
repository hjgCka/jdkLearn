package enums;

import java.util.EnumSet;

public enum SimpleEnum {
    TEST, BOOK, SWIM, RUN;

    public static void main(String[] args) {
        EnumSet<SimpleEnum> set = EnumSet.allOf(SimpleEnum.class);
        //EnumSet<SimpleEnum> set = EnumSet.noneOf(SimpleEnum.class);
        System.out.println(set);
    }
}
