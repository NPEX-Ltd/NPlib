package np.library.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodePrinter {
	public static void PrintAllChildren(int depth, Node node) {
		if(depth > 1) return;
		NodeList children = node.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			for(int indentLvl = 0; indentLvl < depth; indentLvl++) System.out.print("-");
			System.out.println("<"+children.item(i).getNodeName()+">");
			if(node.hasChildNodes()) PrintAllChildren(depth + 1, children.item(i));
		}
	}
}
