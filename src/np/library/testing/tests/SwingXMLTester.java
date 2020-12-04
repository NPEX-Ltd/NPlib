package np.library.testing.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import np.library.gui.SwingXML;
import np.library.testing.Test;
import np.library.xml.NodePrinter;
import np.library.xml.XMLFile;

public class SwingXMLTester {

	@Test
	public void testConstruction() {
		XMLFile file = XMLFile.Load("resources/ui2.xml");
		Element root = null;
		if(file.GetNodes().item(0) instanceof Element) {
			root = (Element) file.GetNodes().item(0);
		}
		
		NodePrinter.PrintAllChildren(0, root);
		
		Map<String, ActionListener> events = new HashMap<>();
		
		events.put("onSubmit", this::OnSubmit);
		
		SwingXML ui = new SwingXML(events);
		ui.AddComponentFromNode(ui.GetJFrame(), root);
		ui.GetJFrame().setVisible(true);
	}
	
	private void OnSubmit(ActionEvent event) {
		System.out.println("Submitted...");
	}
}
