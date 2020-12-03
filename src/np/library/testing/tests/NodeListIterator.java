package np.library.testing.tests;

import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListIterator implements Iterable<Node> {

	private NodeIterator iterator;
	
	
	
	public NodeListIterator(NodeList list) {
		super();
		this.iterator = new NodeIterator(list);
	}

	@Override
	public Iterator<Node> iterator() {
		return iterator();
	}
	
	public static class NodeIterator implements Iterator<Node> {
		NodeList list;
		int ptr = 0;
		
		public NodeIterator(NodeList list) {
			super();
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			return ptr > list.getLength();
		}

		@Override
		public Node next() {
			return list.item(ptr++);
		}
		
	}

}
