package com.receiver2d.engine.io;


import javax.xml.parsers.*;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*;
import java.lang.reflect.Field;

import com.receiver2d.engine.*;
import com.receiver2d.engine.Console;
import com.receiver2d.engine.entitysystem.Entity;
import com.receiver2d.engine.entitysystem.EntityList;

/**
 * Useful for dynamically loading various types of data from Receiver2D-specific
 * files.
 */
public class FileManager {
	/**
	 * Parses a Receiver2D World File (.r2dw) and returns the output.
	 * 
	 * @param location
	 * @return A world parsed by XML handling.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws DOMException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static World loadWorld(String location)
			throws ParserConfigurationException, SAXException, IOException,
			IllegalArgumentException, IllegalAccessException, DOMException {
		boolean isValid = location.matches("(.*)[\\.]r2dw$");
		File worldFile = new File(location);

		if (!worldFile.exists()) Console.log("World file does not exist!");
		else if (!isValid) Console.log("World file is not valid!");
		if (!isValid || !worldFile.exists()) return null;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(worldFile);

		// create node list from scenes in world (only look for one)
		Node worldNode = doc.getElementsByTagName("world").item(0);
		World world = null;
		if (worldNode.getAttributes().getNamedItem("name") == null)
			world = new World();
		else world = new World(worldNode.getAttributes().getNamedItem("name")
					.getNodeValue());
		
		// check scene information
		NodeList sceneNodes = doc.getElementsByTagName("scene");
		Console.log("Scene nodes length: " + sceneNodes.getLength());
		Scene[] scenes = new Scene[sceneNodes.getLength()];
		for (int n = 0; n < sceneNodes.getLength(); n++) {
			scenes[n] = new Scene(sceneNodes.item(n).getAttributes()
					.getNamedItem("name").getNodeValue());
			NodeList sceneNodeInfo = sceneNodes.item(n).getChildNodes();
			for (int k = 0; k < sceneNodeInfo.getLength(); k++) {
				Node node = sceneNodeInfo.item(k);
				if (node.getNodeName() == "values") {
					NodeList values = node.getChildNodes();

					for (int i = 0; i < values.getLength(); i++) {
						// check against text nodes and other non-tags
						if (!values.item(i).hasAttributes()) continue;
						// parse value and set
						scenes[n].setValue(values.item(i).getAttributes()
								.getNamedItem("name").getNodeValue(),
								parseValueElement(values.item(i)));
					}
				} else if (node.getNodeName() == "resources") {
					NodeList resources = node.getChildNodes();

					for (int i = 0; i < resources.getLength(); i++) {
						// check against text nodes and other non-tags
						if (!resources.item(i).hasAttributes()) continue;
						Node rnode = resources.item(i);
						NamedNodeMap nnm = rnode.getAttributes();

						Console.log("Loading resource \""
								+ nnm.getNamedItem("path").getNodeValue()
								+ "\"");
						if (nnm.getNamedItem("type") == null)
							world.addResource(new R2DResource(nnm.getNamedItem(
									"path").getNodeValue()));
						else world.addResource(new R2DResource(nnm.getNamedItem(
								"path").getNodeValue(), nnm.getNamedItem(
								"type").getNodeValue()));

					}
				} else if (node.getNodeName() == "entitylist") {
					NodeList entityNodes = node.getChildNodes();
					EntityList entityList = new EntityList();
					
					// loop through all entities

					for (int i = 0; i < entityNodes.getLength(); i++) {
						// check against text nodes and other non-tags
						if (!entityNodes.item(i).hasAttributes()) continue;
						Node enode = entityNodes.item(i);
						Entity en = new Entity();

						NamedNodeMap nnm = enode.getAttributes();
						for (int j = 0; j < nnm.getLength(); j++) {
							// loop through all entity attributes; get field names						
							String[] fNames = nnm.item(j).getNodeName()
									.split("[_]");
							
							Console.log("fNames is "+fNames.length);
														
							// oh great, here's where the real fun comes:
							// prepare yourself for O(2n) goodness! T_T
							
							int aN = fNames.length;	// attributes n
							
							Object[] objs = new Object[aN]; // our objects
							Field[] fields = new Field[aN]; // our fields
							
							// create object, then field, for _n times_
							for (int m = 0; m < aN; m++) {
								objs[m] = m==0 ? en : 
									fields[m-1].get(objs[m-1]); // set obj
								
								// try to get next field in object (is toplevel)
								try {
									fields[m] = objs[m].getClass()
											.getField(fNames[m]); // our fName
									Console.log("Got the field \""+fNames[m]
											+"\" in "+objs[m].getClass()
													.getSimpleName()+".");
								} catch (NoSuchFieldException
										| SecurityException e) {
									Console.log("Could not get field "
										+fNames[m]+". Aborting.");
									break;
								}
							}
							
							// what is our end value?
							String eVal = nnm.item(j).getNodeValue();
							
							// get type of value
							String type = fields[aN-1].get(objs[aN-1])
									.getClass().getSimpleName().toLowerCase();
							switch (type) { // set value
							case "char":
								fields[aN-1].set(objs[aN-1], eVal.charAt(0));
								break;
							case "byte":
								fields[aN-1].set(objs[aN-1], new Byte(eVal));
								break;
							case "short":
								fields[aN-1].set(objs[aN-1], new Short(eVal));
								break;
							case "integer":
								fields[aN-1].set(objs[aN-1], new Integer(eVal));
								break;
							case "long":
								fields[aN-1].set(objs[aN-1], new Long(eVal));
								break;
							case "float":
								fields[aN-1].set(objs[aN-1], new Float(eVal));
								break;
							case "double":
								fields[aN-1].set(objs[aN-1], new Double(eVal));
								break;
							case "boolean":
								fields[aN-1].set(objs[aN-1], new Boolean(eVal));
								break;
							case "string":
								fields[aN-1].set(objs[aN-1], eVal);
								break;
							default:
								Console.log("Could not load value!");
								break;
							}
							
							Console.log("Using type "+type+" for value "+eVal);
							
							// set fields now, in bottom-up _n-1 times_
							for (int m = aN-2; m >= 0; m--)
								fields[m].set(objs[m], objs[m+1]);
							
							Console.log("Loaded values into entity.");
						}
						
						NodeList componentNodes = enode.getChildNodes();
						for (int j=0; j<componentNodes.getLength(); j++) {
							// TODO: add components
							
						}
						
						entityList.add(en);
						Console.log("Loaded entity \""+en.name+"\".");
					}
					scenes[n].setEntityList(entityList);
				}
			}
		}

		world.addScenes(scenes);
		return world;
	}

	/**
	 * Take a particular XML element of convention <datatype>info</datatype> and
	 * interpret it as a value with a specific type.
	 * 
	 * @param node	The node to interpret.
	 * @return 		The node's value and dataType.
	 */
	private static Object parseValueElement(Node node) {
		String dataType = node.getNodeName();
		String val = node.getTextContent();

		Object nVal = null;
		switch (dataType) {
		case "int":
			nVal = new Integer(val);
			break;
		case "short":
			nVal = new Short(val);
			break;
		case "long":
			nVal = new Long(val);
			break;
		case "boolean":
			nVal = new Boolean(val);
			break;
		case "float":
			nVal = new Float(val);
			break;
		case "double":
			nVal = new Double(val);
			break;
		case "char":
			try {
				char numVal = (char) Integer.parseInt(val);
				nVal = numVal;
			} catch (Exception e) {
				nVal = val.charAt(0);
			}
			break;
		case "byte":
			nVal = new Byte(val);
			break;
		default:
			nVal = val; //nVal is a String type
		}

		return nVal;
	}
}
