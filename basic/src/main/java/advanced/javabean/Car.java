package advanced.javabean;

import lombok.ToString;

import java.beans.*;

/**
 * @Description
 * @Author hjg
 * @Date 2025-06-16 23:48
 */
@ToString
public class Car {
    private String name;
    private String color;

    private PropertyChangeSupport mPcs = new PropertyChangeSupport(this);
    private VetoableChangeSupport mVcs = new VetoableChangeSupport(this);

    public String getName() {
        return name;
    }

    public void setName(String name) throws PropertyVetoException {
        String oldName = this.name;
        mVcs.fireVetoableChange("name", oldName, name);

        this.name = name;
        mPcs.firePropertyChange("name", oldName, name);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) throws PropertyVetoException {
        String oldColor = this.color;
        mVcs.fireVetoableChange("color", oldColor, color);

        this.color = color;
        mPcs.firePropertyChange("color", oldColor, color);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        mVcs.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        mVcs.removeVetoableChangeListener(listener);
    }
}
