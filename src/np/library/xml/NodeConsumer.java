package np.library.xml;

import org.w3c.dom.Node;

public interface NodeConsumer {
	public void Call(int depth, Node node);
}
