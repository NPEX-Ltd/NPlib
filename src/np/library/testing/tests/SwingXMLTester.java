package np.library.testing.tests;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import np.library.gui.ActionMap;
import np.library.gui.SwingXML;
import np.library.gui.SwingXMLActionListener;
import np.library.gui.SwingXMLFactory;
import np.library.testing.Test;

public class SwingXMLTester {

	@Test
	public void testConstruction() {
		ActionMap<SwingXMLActionListener> actions = new ActionMap<>();
		actions.put("onSubmit", new SwingXMLActionListener(this::OnSubmit));
		
		SwingXML ui = SwingXMLFactory.CreateNewUI("resources/ui2.xml", actions);
		ui.Show();
		
		//ui.Close();
	}
	
	private void OnSubmit(SwingXML ui, ActionEvent event) {
		System.out.println("Submitted...");
		JTextArea txtMessageBox = ui.GetComponentByID("txtMessageBox");
		System.out.println("Text: "+txtMessageBox.getText());
	}
}
