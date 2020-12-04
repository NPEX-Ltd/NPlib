package np.library.testing.tests;

import org.w3c.dom.Node;

import np.library.exceptions.FileException;
import np.library.testing.Test;
import np.library.testing.Tester;
import np.library.xml.PrettyPrinter;
import np.library.xml.XMLFile;

public class XMLTester {
	@Test
	public void testXML() {
		try {
			XMLFile file = XMLFile.Load("resources/ui.xml");
			System.out.println(PrettyPrinter.prettyPrintXml("resources/ui.xml"));
			System.out.println(file.GetNodes().getLength());
			NodeListIterator it = new NodeListIterator(file.GetNodes());
			for(Node node : it) {
				System.out.println("Node: "+node);
			}
		} catch (FileException fex) {
			Tester.Fail("Unable To Load File...");
		}
	}
}
