package com.receiver2d.engine.io;

import com.receiver2d.engine.*;

import java.io.File;
import java.io.IOException;
import java.util.regex.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

/**
 * Useful for dynamically loading various types of data from Receiver2D-specific
 * files.
 */
public class FileManager {
	/**
	 * Parses a Receiver2D Scene File (.r2ds) and returns the output.
	 * @param location
	 * @return A series of scenes.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public Scene[] loadScene(String location) throws Exception {
		boolean isValid = Pattern.matches("\\.r2ds$", location);
		File sceneFile = new File(location);
		if (!isValid || !sceneFile.exists()) return null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(sceneFile);
		
		// create node list from scenes in world
		NodeList sceneNodes = doc.getElementsByTagName("scene");
		Scene[] scenes = new Scene[sceneNodes.getLength()];
		
		// check scene information
		for (int n=0; n<sceneNodes.getLength(); n++) {
			scenes[n] = new Scene(sceneNodes.item(n).getAttributes()
					.getNamedItem("name").getNodeValue());
			NodeList sceneNodeInfo = sceneNodes.item(n).getChildNodes();
			for (int k=0; k<sceneNodeInfo.getLength(); k++) {
				Node node = sceneNodeInfo.item(k);
				if (node.getNodeName() == "values") {
					NodeList values = node.getChildNodes();
					for (int i=0; i<values.getLength(); i++) {
						// iterate through all values; add to scenes[n]
					}
				} else if (node.getNodeName() == "resources") {
					NodeList resources = node.getChildNodes();
					for (int i=0; i<resources.getLength(); i++) {
						// iterate through all resources; add to scenes[n]
					}
				} else if (node.getNodeName() == "entitylist") {
					NodeList entities = node.getChildNodes();
					for (int i=0; i<entities.getLength(); i++) {
						// iterate through all entities; add to scenes[n]
					}
				}
			}
		}
		return scenes; // TODO: fix
	}
}
