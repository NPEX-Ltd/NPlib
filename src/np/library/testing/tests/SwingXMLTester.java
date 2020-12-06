package np.library.testing.tests;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import np.library.common.Async;
import np.library.common.Time;
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
		if(TestingMain.ANT_MODE) {
			ui.Close();
		} else {
			ui.SetMaster();
			
			Async.DispatchDaemon("Printer", () -> {
				JTextArea txtMessageBox = ui.GetComponentByID("txtMessageBox");
				for(;;) {
					Time.SleepMillis(1000);
					txtMessageBox.append("Tick!\n");
				}
			});
			
		}
	}
	
	private void OnSubmit(SwingXML ui, ActionEvent event) {
		JTextField txtfInput = ui.GetComponentByID("txtfInput");
		JTextArea txtMessageBox = ui.GetComponentByID("txtMessageBox");
		txtMessageBox.append(txtfInput.getText() + "\n");
	}
}
