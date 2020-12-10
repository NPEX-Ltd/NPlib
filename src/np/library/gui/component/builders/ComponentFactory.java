package np.library.gui.component.builders;


import org.w3c.dom.Node;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.exceptions.JuggledException;

@API(level = Level.ALPHA)
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
