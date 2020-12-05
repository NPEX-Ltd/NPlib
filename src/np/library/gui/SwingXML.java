package np.library.gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import org.w3c.dom.*;


public class SwingXML {
	private JFrame frame = new JFrame();
	private Map<String, JComponent> components = new HashMap<>(); 
	private Map<String, SwingXMLActionListener> eventHandlers;
	
	
	
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

	public void AddJPanelFromNode(Container parent, Node node) {
		String id = GetAttribute(node, "id");
		JPanel panel = new JPanel();
		parent.add(panel, GetBorderLayoutLocation(node));
		components.put(id, panel);
		RunThroughChildren(node.getNodeName(), panel, node);
	}
	
	@SuppressWarnings("deprecation")
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
		
		panel.resize(width, height);
	}
	
	public void AddJPanelFromNode(JFrame parent, Element node) {
		AddJPanelFromNode(parent.getRootPane(), node);
	}
	
	public void AddJTextArea(Container parent, Node node) {
		String id = GetAttribute(node, "id");
		String content = GetContent(node);
		int colums = GetWidth(node);
		int rows = GetHeight(node);
		JTextArea textArea = new JTextArea(content);
		textArea.setSize(colums, rows);
		int fontSize = textArea.getFont().getSize();
		textArea.setPreferredSize(new Dimension(colums * fontSize, rows * fontSize));
	
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
			return (T) components.get(id);
		} catch (ClassCastException ccex) {
			System.err.println("Unable To Retrieve Component '"+id+"'");
			System.exit(-10);
		}
		return null;
	}
	
	private void RunThroughChildren(String parentType, Container parent, Node node) {
		NodeList children = node.getChildNodes();
		System.out.println("Node Length: "+children.getLength());
		for(int i = 0; i < children.getLength(); i++) {
			System.out.println(children.item(i).getNodeName());
			AddComponentFromNode(parent, children.item(i));
		}
	}
	
	private String GetAttribute(Node node, String key) {
		if(HasAttribute(node, key))
			return node.getAttributes().getNamedItem(key).getNodeValue();
		else
			return "";
	} 
	
	private String GetContent(Node node) {
		return node.getTextContent();
	}
	
	private String GetBorderLayoutLocation(Node node) {
		String value = "";
		
		switch(GetAttribute(node, "location")) {
		case "CENTER": value = BorderLayout.CENTER; break;
		case "NORTH": value = BorderLayout.NORTH; break;
		case "SOUTH": value = BorderLayout.SOUTH; break;
		case "EAST": value = BorderLayout.EAST; break;
		case "WEST": value = BorderLayout.WEST; break;
		}
		
		return value;
	}
	
	private int GetVerticalScrollPanePolicy(Node node) {
		int value = 0;
		switch(GetAttribute(node, "VS")) {
		case "ALWAYS": value = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS; break;
		case "ASNEEDED": value = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED; break;
		case "NEVER": value = JScrollPane.VERTICAL_SCROLLBAR_NEVER; break;
		}
		return value;
	}
	
	private int GetHorizontalScrollPanePolicy(Node node) {
		int value = 0;
		switch(GetAttribute(node, "HS")) {
		case "ALWAYS": value = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS; break;
		case "ASNEEDED": value = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED; break;
		case "NEVER": value = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER; break;
		}
		return value;
	}
	
	public int GetWidth(Node node) {
		String size = GetAttribute(node, "size");
		if(size.isEmpty()) size = "0,0";
		return Integer.parseInt(size.split(",")[0]);
	}
	
	public int GetHeight(Node node) {
		String size = GetAttribute(node, "size");
		if(size.isEmpty()) size = "0,0";
		return Integer.parseInt(size.split(",")[1]);
	}
	
	public boolean GetBooleanAttrib(Node node, String key) {
		if(HasAttribute(node, key)) {
			String value = GetAttribute(node, key);
			if(value.equalsIgnoreCase("TRUE")) return true;
			if(value.equalsIgnoreCase("FALSE")) return false;
		}
		return false;
	} 
	
	private boolean HasAttribute(Node node, String key) {
		return node.getAttributes().getNamedItem(key) != null;
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
