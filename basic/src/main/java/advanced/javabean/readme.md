### 参考文档

https://docs.oracle.com/javase/tutorial/javabeans/writing/properties.html

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
