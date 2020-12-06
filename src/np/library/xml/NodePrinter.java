package np.library.xml;

import org.w3c.dom.Node;

public class NodePrinter {
	public static void PrintAllChildren(int depth, Node node) {
		NodeTraverser.ConsumeAll(node, NodePrinter::Consume);
	}
	
	private static void Consume(int depth, Node node) {
		for(int indentLvl = 0; indentLvl < depth; indentLvl++) System.out.print("-");
		System.out.println("<"+node.getNodeName()+">");
	}
}
