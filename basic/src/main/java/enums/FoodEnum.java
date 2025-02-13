package enums;

public enum FoodEnum {

    WATCH,
    PEN("pen"),
    BIKE("bike"),
    CAR("car");

    private String name;

    /**
     * 构造函数默认为private
     */
    FoodEnum() {
        this.name = "***";
    }

    FoodEnum(String name) {
        this.name = name;
    }
}
