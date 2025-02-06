package generic;

import lombok.Data;

/**
 * @Description
 * @Author hjg
 * @Date 2025-01-25 9:52
 */
@Data
public class Fruit {
    private String name;

    public void draw() {
        System.out.println(name);
    }
}
