package np.library.gui.component.builders;

import java.awt.Container;

import javax.swing.JPanel;

import org.w3c.dom.Node;
import static np.library.xml.NodeUtils.*;
public class JPanelFactory {
	public static JPanel Build(Node node, Container parent) {
		JPanel panel = new JPanel();
		String location = "";
		if(HasAttribute(node, "location")) {
			 location = GetBorderLayoutLocation(node);
		}
		
		parent.add(panel, location);
		return panel;
	}
}
