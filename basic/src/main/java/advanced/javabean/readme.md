### 参考文档

https://docs.oracle.com/javase/tutorial/javabeans/writing/properties.html

https://docs.oracle.com/javase/tutorial/javabeans/advanced/customization.html

### Bound Properties

A bound property notifies listeners when its value changes. This has two implications:

1. The bean class includes addPropertyChangeListener() and removePropertyChangeListener() methods for managing the bean's listeners.
2. When a bound property is changed, the bean sends a PropertyChangeEvent to its registered listeners.

当bean中的属性变更时，通过PropertyChangeListener 来获得通知。

### Constrained Properties

A constrained property is a special kind of bound property. For a constrained property, the bean keeps track of a set of veto listeners. 
When a constrained property is about to change, the listeners are consulted about the change. Any one of the listeners has a chance to veto the change, in which case the property remains unchanged.

当bean中属性变更时，通过VetoableChangeListener 来否决变更。

通过在listener中抛出PropertyVetoException异常。

### BeanInfo
组件尤其是图像组件，可以拥有大量的属性，尤其是组件继承Component, JComponent, 或其它swing类时。

即便是构建工具也很难找到正确的属性来编辑，BeanInfo类可以改变bean在构建工具中的出现。

构建工具查询BeanInfo类来找出哪些属性应该被展示 而 哪些属性应该被隐藏。

### Bean Customization
自定义提供了一种方法来修改应用构建工具中bean的外观和行为。有如下几个类：
- PropertyEditor
- PropertyEditorSupport
- PropertyEditorManager
- Customizer

一个bean的外观和行为在兼容bean的构建工具中，可以在设计时被自定义。

属性编辑器实现PropertyEditor接口，提供方法指明一个属性如何在属性表格中展示。

比如在构建工具中 foreground  和 background ，就弹出颜色选择盒。而toolTipText 属性打开一个字符串编辑器窗口。

PropertyEditorSupport 为PropertyEditor提供了默认的实现。

还可以创建自定义的属性编辑器。

