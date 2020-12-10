package np.library.gui;



import java.awt.event.ActionEvent;

import javax.swing.*;

import np.library.annotations.API;
import np.library.annotations.API.Level;
@API(level = Level.ALPHA)
public class SwingXMLActionListener{
	protected SwingXML ui;
	private SwingXMLEventHandler handler;
	public SwingXMLActionListener() {}
	
	public SwingXMLActionListener(SwingXMLEventHandler handler) {
		this.handler = handler;
	}

	public void OnEvent(ActionEvent event) {
		handler.Invoke(ui, event);
	}
	
	public void SetTrigger(JButton button) { button.addActionListener(this::OnEvent); }
	public void SetHostUI(SwingXML ui) { this.ui = ui; }
	
	
	
	
}
