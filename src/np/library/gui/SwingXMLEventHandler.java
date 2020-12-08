package np.library.gui;

import java.awt.event.ActionEvent;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public interface SwingXMLEventHandler {
	public void Invoke(SwingXML ui, ActionEvent event);
}
