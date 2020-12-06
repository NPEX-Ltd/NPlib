package np.library.gui;

import java.awt.Container;

import javax.swing.*;

import org.w3c.dom.Node;
import static np.library.xml.NodeUtils.*;
public class JTextFieldFactory extends ComponentFactory<JTextField> {

	@Override
	public JTextField Construct(Node node) {
		String location = GetBorderLayoutLocation(node);
		JTextField textField = new JTextField();
		int width = GetWidth(node);
		textField.setColumns(width);
		return textField;
	}

}
