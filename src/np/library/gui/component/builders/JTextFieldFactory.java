package np.library.gui.component.builders;


import javax.swing.*;


import org.w3c.dom.Node;

import np.library.annotations.API;
import np.library.annotations.API.Level;

import static np.library.xml.NodeUtils.*;

@API(level = Level.ALPHA)
public class JTextFieldFactory extends ComponentFactory<JTextField> {

	@Override
	public JTextField Construct(Node node) {
		JTextField textField = new JTextField();
		int width = GetWidth(node);
		textField.setColumns(width);
		return textField;
	}

}
