package np.library.xml;

import org.w3c.dom.Node;

import np.library.annotations.API;
import np.library.annotations.API.Level;
@API(level = Level.ALPHA)
public interface NodeConsumer {
	public void Call(int depth, Node node);
}
