package np.library.gui.component.builders;
import javax.swing.JPanel;

import org.w3c.dom.Node;
public class JPanelFactory extends ComponentFactory<JPanel> {
	
	@Override
	public JPanel Construct(Node node) {
		JPanel panel = new JPanel();
		return panel;
	}
}
