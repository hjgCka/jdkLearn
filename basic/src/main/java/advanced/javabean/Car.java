package advanced.javabean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @Description
 * @Author hjg
 * @Date 2025-06-16 23:48
 */
public class Car {
    private String name;
    private String color;

    private PropertyChangeSupport mPcs = new PropertyChangeSupport(this);


    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        mPcs.firePropertyChange("name", oldName, this.name);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String oldColor = this.color;
        this.color = color;
        mPcs.firePropertyChange("color", oldColor, this.color);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }
}
