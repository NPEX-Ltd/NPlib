package np.library.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import org.w3c.dom.*;


public class SwingXML {
	private JFrame frame = new JFrame();
	private Map<String, JComponent> components = new HashMap<>(); 
	private Map<String, ActionListener> eventHandlers;
	
	public SwingXML(Map<String, ActionListener> eventHandlers) {
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
		}
	}

	
	private void AddJButton(Container parent, Node node) {
		String id = GetAttribute(node, "id");
		String text = GetContent(node);
		String eventHandlerID = GetAttribute(node, "onClick");
		ActionListener eventHandler = eventHandlers.get(eventHandlerID);
		JButton button = new JButton(text);
		button.addActionListener(eventHandler);
		parent.add(button, GetBorderLayoutLocation(node));
		components.put(id, button);
	}
	
	private void AddJFrameFromNode(Node node) {
		String title = GetAttribute(node, "title");
		String size = GetAttribute(node, "size");
		int width = Integer.parseInt(size.split(",")[0]);
		int height = Integer.parseInt(size.split(",")[1]);
		
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RunThroughChildren(node.getNodeName(), frame, node);
	}

	public void AddJPanelFromNode(Container parent, Node node) {
		String id = GetAttribute(node, "id");
		String location = GetAttribute(node, "location");
		JPanel panel = new JPanel();
		parent.add(panel, GetBorderLayoutLocation(node));
		components.put(id, panel);
		RunThroughChildren(node.getNodeName(), panel, node);
	}
	
	public void AddJPanelFromNode(JFrame parent, Element node) {
		AddJPanelFromNode(parent.getRootPane(), node);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T GetComponentByID(String id) {
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
		if(node.getAttributes().getNamedItem(key) != null)
			return node.getAttributes().getNamedItem(key).getNodeValue();
		else
			return "";
	} 
	
	private String GetContent(Node node) {
		return node.getTextContent();
	}
	
	private String GetBorderLayoutLocation(Node node) {
		String value = BorderLayout.CENTER;
		
		switch(GetAttribute(node, "location")) {
		case "CENTER": value = BorderLayout.CENTER; break;
		case "NORTH": value = BorderLayout.NORTH; break;
		case "SOUTH": value = BorderLayout.SOUTH; break;
		case "EAST": value = BorderLayout.EAST; break;
		case "WEST": value = BorderLayout.WEST; break;
		}
		
		return value;
	}
}
