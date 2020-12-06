package np.library.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import np.library.annotations.API;
import np.library.exceptions.FileException;
import static np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class XMLFile {
	private Document doc;
	private XMLFile(String path) throws Exception {
		File xmlFile = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        doc = dBuilder.parse(xmlFile);
	}
	
	public static XMLFile Load(String path) {
		try {
			return new XMLFile(path);
		} catch (Exception ex) {
			throw new FileException(ex);
		}
	}
	
	public NodeList GetNodes() {
		return doc.getChildNodes();
	}

	public Node GetRoot() {
		return doc;
	}
}