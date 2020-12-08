package np.library.xml;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import org.w3c.dom.Node;

import np.library.annotations.API;
import np.library.annotations.API.Level;
@API(level = Level.ALPHA)
public class NodeUtils {
	public static String GetAttribute(Node node, String key) {
		if(HasAttribute(node, key))
			return node.getAttributes().getNamedItem(key).getNodeValue();
		else
			return "";
	} 
	
	public static String GetContent(Node node) {
		return node.getTextContent();
	}
	
	public static String GetBorderLayoutLocation(Node node) {
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
	
	public static int GetVerticalScrollPanePolicy(Node node) {
		int value = 0;
		switch(GetAttribute(node, "VS")) {
		case "ALWAYS": value = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS; break;
		case "ASNEEDED": value = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED; break;
		case "NEVER": value = JScrollPane.VERTICAL_SCROLLBAR_NEVER; break;
		}
		return value;
	}
	
	public static int GetHorizontalScrollPanePolicy(Node node) {
		int value = 0;
		switch(GetAttribute(node, "HS")) {
		case "ALWAYS": value = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS; break;
		case "ASNEEDED": value = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED; break;
		case "NEVER": value = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER; break;
		}
		return value;
	}
	
	public static int GetWidth(Node node) {
		String size = GetAttribute(node, "size");
		if(size.isEmpty()) size = "0,0";
		return Integer.parseInt(size.split(",")[0]);
	}
	
	public static int GetHeight(Node node) {
		String size = GetAttribute(node, "size");
		if(size.isEmpty()) size = "0,0";
		return Integer.parseInt(size.split(",")[1]);
	}
	
	public static boolean GetBooleanAttrib(Node node, String key) {
		if(HasAttribute(node, key)) {
			String value = GetAttribute(node, key);
			if(value.equalsIgnoreCase("TRUE")) return true;
			if(value.equalsIgnoreCase("FALSE")) return false;
		}
		return false;
	} 
	
	public static boolean HasAttribute(Node node, String key) {
		return node.getAttributes().getNamedItem(key) != null;
	}
}
