package advanced.cglib;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Description
 * @Author hjg
 * @Date 2025-06-17 12:47
 */
public abstract class AbstractCar {
    @Getter @Setter
    private String brand;

    public abstract BigDecimal getPrice();
}
