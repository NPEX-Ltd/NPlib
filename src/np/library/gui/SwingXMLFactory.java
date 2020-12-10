package np.library.gui;


import org.w3c.dom.Element;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.xml.XMLFile;

@API(level = Level.ALPHA)
public class SwingXMLFactory {
	public static SwingXML CreateNewUI(String path, ActionMap<SwingXMLActionListener> actions) {
		SwingXML ui = new SwingXML(actions.ToHashMap());
		XMLFile file = XMLFile.Load(path);
		Element root = null;
		if(file.GetNodes().item(0) instanceof Element) {
			root = (Element) file.GetNodes().item(0);
		}
		ui.AddComponentFromNode(ui.GetJFrame(), root);
		return ui;
	}
}
