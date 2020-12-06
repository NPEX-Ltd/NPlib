package np.library.gui.component.builders;
import java.awt.Container;

import javax.swing.JPanel;

import org.w3c.dom.Node;

import np.library.gui.ComponentFactory;

import static np.library.xml.NodeUtils.*;
public class JPanelFactory extends ComponentFactory<JPanel> {
	
	@Override
	public JPanel Construct(Container parent, Node node) {
		JPanel panel = new JPanel();
		String location = "";
		if(HasAttribute(node, "location")) {
			 location = GetBorderLayoutLocation(node);
		}
		
		parent.add(panel, location);
		return panel;
	}
}
