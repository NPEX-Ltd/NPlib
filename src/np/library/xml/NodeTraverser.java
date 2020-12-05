package np.library.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeTraverser {
	public static void ConsumeAll(Node root, NodeConsumer consumer) {
		ConsumeAll(0, root, consumer);
	}
	
	public static void ConsumeAll(int depth, Node root, NodeConsumer consumer) {
		consumer.Call(depth, root);
		if(root.hasChildNodes()) {
			NodeList children = root.getChildNodes();
			for(int i = 0; i < children.getLength(); i++) {
				if(root.hasChildNodes()) ConsumeAll(depth + 1, children.item(i), consumer);
			}
		}
	}
}
