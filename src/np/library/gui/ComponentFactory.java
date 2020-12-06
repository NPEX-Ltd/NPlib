package np.library.gui;

import java.awt.Container;

import org.w3c.dom.Node;

import np.library.exceptions.JuggledException;

public abstract class ComponentFactory<T> {
	@SuppressWarnings("deprecation")
	public static <T> ComponentFactory<T> GetFactoryOf(Class<? extends ComponentFactory<T>> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception ex) {
			throw new JuggledException(ex);
		}
	} 
	public abstract T Construct(Node node);
}
