package np.library.testing.tests;


import np.library.exceptions.FileException;
import np.library.testing.Test;
import np.library.testing.Tester;
import np.library.xml.NodePrinter;
import np.library.xml.XMLFile;

public class XMLTester {
	@Test
	public void testXML() {
		try {
			XMLFile file = XMLFile.Load("resources/ui.xml");
			NodePrinter.PrintAllChildren(0, file.GetRoot());
		} catch (FileException fex) {
			Tester.Fail("Unable To Load File...");
		}
	}
}
