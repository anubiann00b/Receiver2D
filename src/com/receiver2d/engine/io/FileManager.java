package com.receiver2d.engine.io;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.receiver2d.engine.Console;
import com.receiver2d.engine.Scene;
import com.receiver2d.engine.World;
import com.receiver2d.engine.entitysystem.Entity;

/**
 * Useful for dynamically loading various types of data from Receiver2D-specific files.
 */
public class FileManager {
	/**
	 * Parses a Receiver2D World File (.r2dw) and returns the output.
	 * 
	 * @param location
	 * @return A world.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static World loadWorld(String location)
			throws ParserConfigurationException, SAXException, IOException {
		boolean isValid = location.matches("(.*)[\\.]r2dw$");
		File worldFile = new File(location);

		if (!worldFile.exists()) Console.debug("World file does not exist!");
		else if (!isValid)
			Console.debug("World file is not valid!");

		if (!isValid || !worldFile.exists()) return null;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(worldFile);

		// create node list from scenes in world
		NodeList sceneNodes = doc.getElementsByTagName("scene");
		Scene[] scenes = new Scene[sceneNodes.getLength()];

		// check scene information
		for (int n = 0; n < sceneNodes.getLength(); n++) {
			scenes[n] = new Scene(sceneNodes.item(n).getAttributes().getNamedItem("name").getNodeValue());
			NodeList sceneNodeInfo = sceneNodes.item(n).getChildNodes();
			for (int k = 0; k < sceneNodeInfo.getLength(); k++) {
				Node node = sceneNodeInfo.item(k);
				if (node.getNodeName() == "values") {
					NodeList values = node.getChildNodes();
					// iterate through all values; add to scenes[n]
					for (int i = 0; i < values.getLength(); i++)
						scenes[n].setValue(values.item(i).getNodeName(),
								values.item(i).getNodeValue());
				} else if (node.getNodeName() == "resources") {
					NodeList resources = node.getChildNodes();
					for (int i = 0; i < resources.getLength(); i++) {

						// iterate through all resources; add to scenes[n]
					}
				} else if (node.getNodeName() == "entitylist") {
					NodeList entities = node.getChildNodes();
					for (int i = 0; i < entities.getLength(); i++) {
						// iterate through all entities; add to scenes[n]
						Entity entity = new Entity();

					}
				}
			}
		}
		World world = new World("Test");
		for (Scene s : scenes)
			world.scenes.add(s);
		return world;
	}
}
