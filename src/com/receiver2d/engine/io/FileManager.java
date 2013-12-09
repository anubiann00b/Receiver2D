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
import com.receiver2d.engine.entitysystem.Component;
import com.receiver2d.engine.entitysystem.Entity;
import com.receiver2d.engine.entitysystem.EntityList;
import com.receiver2d.engine.entitysystem.components.*;

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
						
						// load entity
						Entity en = (Entity) loadResourceFromNode(enode,
								"com.receiver2d.engine.entitysystem.Entity");
						
						NodeList componentNodes = enode.getChildNodes();
						
						Console.log("Loading Components now...");
						// add components to entity
						for (int j=0; j<componentNodes.getLength(); j++) {
							Node cnode = componentNodes.item(j);
							// check against text nodes and other non-tags
							if (!cnode.hasAttributes()) continue;
							
							String cType = cnode.getAttributes()
									.getNamedItem("type").getNodeValue();

							Object loaded = loadResourceFromNode(cnode,
									"com.receiver2d.engine.entitysystem.components."
											+ cType);
							
							// check for supported Component type
							if (loaded == null) continue;

							Component comp = null;
							
							// our Component is supported; check now
							switch (cType) {
							case "Rigidbody":
								comp = (Rigidbody) loaded;
								break;
							case "Collider":
								comp = (Collider) loaded;
								break;
							case "CharacterController":
								comp = (CharacterController) loaded;
								break;
							case "Script":
								comp = (Script) loaded;
								break;
							}
							
							en.attachComponent(comp);
							
							Console.log("Added component "+cType+" to entity.");
						}
						
						// add to our list
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
	
	/**
	 * Extracts information from a given node and converts it into a resource.
	 * 
	 * @param node The node (an XML element node) to analyze.
	 * @param oType The type of object to extract information from.
	 * @param toIgnore The names of any attributes to ignore (these will not be
	 * 					treated as fields when using reflection).
	 * @return An object whose fields are loaded from information in the node.
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	
	public static Object loadResourceFromNode(Node node, String oType,
			String... toIgnore)
			throws IllegalArgumentException, IllegalAccessException {
		NamedNodeMap nnm = node.getAttributes();
		
		// create new oType object
		Object o = null;
		try {
			o = Class.forName(oType).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			Console.log("Could not create class \""+oType+"\" for resource.");
			return null;
		}
		
		// loop through attributes in nnm
		f1: for (int i=0; i<nnm.getLength(); i++) {
			// check for conflict with blacklist
			for (String block : toIgnore)
				if (block == nnm.item(i).getNodeName())
					break f1; // quit attrib loop if there is one
			// loop through all entity attributes; get field names
			String[] fNames = nnm.item(i).getNodeName().split("[_]");
			Console.debug("Node name: "+nnm.item(i).getNodeName());
			
			int aN = fNames.length;	// attributes n
			
			Object[] objs = new Object[aN]; // our objects
			Field[] fields = new Field[aN]; // our fields
			
			// populate objs, fields for n times
			for (int j=0; j<aN; j++) {
				objs[j] = j==0 ? o : fields[j-1].get(objs[j-1]);
				
				// try to get next field in object (is toplevel)
				try {
					fields[j] = objs[j].getClass().getField(fNames[j]);
					Console.debug("Got the field \"" + fNames[j] + "\" in "
							+ objs[j].getClass().getSimpleName() + ".");
				} catch (NoSuchFieldException | SecurityException e) {
					Console.debug("Could not get field \"" + fNames[j]
							+ "\". Continuing...");
					continue f1;
				}
			}
			
			// what is our end value?
			String eVal = nnm.item(i).getNodeValue();
			
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
			
			Console.debug("Using type \""+type+"\" for value "+eVal);
			
			// the below code is unnecessary due to passing by reference:
			// set fields now, in bottom-up _n-1 times_
//			for (int j = aN-2; j >= 0; j--)
//				fields[j].set(objs[j], objs[j+1]);
			
			Console.log("Loaded values into "+o.getClass().getSimpleName());
		}
		
		return o;
	}	
}
