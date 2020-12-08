package np.library.gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import org.w3c.dom.*;

import np.library.gui.component.builders.ComponentFactory;
import np.library.gui.component.builders.JPanelFactory;
import np.library.gui.component.builders.JTextFieldFactory;

import static np.library.xml.NodeUtils.*;

public class SwingXML {
	private JFrame frame = new JFrame();
	private Map<String, JComponent> components = new HashMap<>(); 
	private Map<String, SwingXMLActionListener> eventHandlers;
	
	private ComponentFactory<JPanel> panelFactory = ComponentFactory.GetFactoryOf(JPanelFactory.class);
	private ComponentFactory<JTextField> textFieldFactory = ComponentFactory.GetFactoryOf(JTextFieldFactory.class);
	
	
	public SwingXML(Map<String, SwingXMLActionListener> eventHandlers) {
		super();
		this.eventHandlers = eventHandlers;
	}

	public JFrame GetJFrame() { return frame; }
	
	public void AddComponentFromNode(Container parent, Node node) {
		
		if(parent == null) parent = frame;
		System.out.println("Attempting To Add Component: "+node.getNodeName());
		switch (node.getNodeName()) {
		case "JFrame": AddJFrameFromNode(node); break;
		case "JPanel": AddJPanelFromNode(parent, node); break;
		case "JButton": AddJButton(parent, node); break;
		case "JTextArea": AddJTextArea(parent, node); break;
		case "JScrollPanel": AddJScrollPanelFromNode(parent, node); break;
		case "JTextField": AddJTextField(parent, node); break;
		}
	}

	
	private void AddJButton(Container parent, Node node) {
		String id = GetAttribute(node, "id");
		String text = GetContent(node);
		String eventHandlerID = GetAttribute(node, "onClick");
		SwingXMLActionListener listener = eventHandlers.get(eventHandlerID);
		JButton button = new JButton(text);
		
		if(HasAttribute(node, "icon")) {
			button.setIcon(new ImageIcon(GetAttribute(node, "icon")));
		}
		
		if(HasAttribute(node, "onClick")) { listener.SetTrigger(button); }
		listener.SetHostUI(this);
		parent.add(button, GetBorderLayoutLocation(node));
		components.put(id, button);
	}
	
	private void AddJFrameFromNode(Node node) {
		String title = GetAttribute(node, "title");
		int width = GetWidth(node);
		int height = GetHeight(node);
		
		frame.setTitle(title);
		frame.setSize(width, height);
		
		RunThroughChildren(node.getNodeName(), frame, node);
	}
	
	private void AddJTextField(Container parent, Node node) {
		String id = GetAttribute(node, "id");
		JTextField field = textFieldFactory.Construct(node);
		parent.add(field, GetBorderLayoutLocation(node));
		RegisterComponent(id, field);
	}
	
	

	public void AddJPanelFromNode(Container parent, Node node) {
		String id = GetAttribute(node, "id");
		JPanel panel = panelFactory.Construct(node);
		parent.add(panel, GetBorderLayoutLocation(node));
		components.put(id, panel);
		RunThroughChildren(node.getNodeName(), panel, node);
	}
	
	public void AddJScrollPanelFromNode(Container parent, Node node) {
		String id = GetAttribute(node, "id");
		int VS, HS;
		VS = GetVerticalScrollPanePolicy(node);
		HS = GetHorizontalScrollPanePolicy(node);
		JScrollPane panel = new JScrollPane(VS, HS);
		int width = GetWidth(node);
		int height = GetHeight(node);
		
		panel.setSize(width, height);
		
		parent.add(panel, GetBorderLayoutLocation(node));
		components.put(id, panel);
		RunThroughChildren(node.getNodeName(), panel, node);
		
		panel.revalidate();
	}
	
	public void AddJPanelFromNode(JFrame parent, Element node) {
		AddJPanelFromNode(parent.getRootPane(), node);
	}
	
	public void AddJTextArea(Container parent, Node node) {
		String id = GetAttribute(node, "id");
		String content = GetContent(node);
		
		JTextArea textArea = new JTextArea(content);
		
		if(HasAttribute(node, "size")) {
			int colums = GetWidth(node);
			int rows = GetHeight(node);
			textArea.setSize(colums, rows);
		}
	
		if(HasAttribute(node, "editable")) {
			System.out.println("node has Editable Attrib...");
			textArea.setEditable(GetBooleanAttrib(node, "editable"));
		}
		
		
		
		if(!(parent instanceof JScrollPane))  {
			parent.add(textArea, GetBorderLayoutLocation(node));
		} else {
			JScrollPane pane = (JScrollPane) parent;
			pane.setViewportView(textArea);
		}
		components.put(id, textArea);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T GetComponentByID(String id) {
		try {
			if(components.containsKey(id)) {
				return (T) components.get(id);
			} else {
				throw new ClassCastException();
			}
		} catch (ClassCastException ccex) {
			System.err.println("Unable To Retrieve Component '"+id+"'");
			System.exit(-10);
		}
		return null;
	}
	
	public void RegisterComponent(String id, JComponent component) {
		components.put(id, component);
	}
	
	private void RunThroughChildren(String parentType, Container parent, Node node) {
		NodeList children = node.getChildNodes();
		System.out.println("Node Length: "+children.getLength());
		for(int i = 0; i < children.getLength(); i++) {
			AddComponentFromNode(parent, children.item(i));
		}
	}
	
	

	public void Show() {
		frame.setVisible(true);
	}
	
	public void Close() {
		frame.dispose();
	}

	public void SetMaster() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
