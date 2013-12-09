package com.receiver2d.engine;

import java.lang.reflect.Field;

import com.receiver2d.engine.Console.LLevel;
import com.receiver2d.engine.entitysystem.Entity;
import com.receiver2d.engine.io.FileManager;
import com.receiver2d.engine.io.R2DResource;
import com.receiver2d.engine.physics.CollisionDetection;
import com.receiver2d.engine.physics.Polygon;

/**
 * This class is for testing purposes only, although it currently is responsible
 * for initializing Receiver2D. NOT FOR RELEASE
 */
public class TestClass {
	/**
	 * Tests various geometry methods.
	 */
	public static void testGeometryMethods() {
		Polygon square = new Polygon(new Vector2D[] { new Vector2D(2, 1),
				new Vector2D(3, 2), new Vector2D(2, 3), new Vector2D(1, 2) });

		for (int i = 0; i <= 10; i++)
			for (int j = 0; j <= 10; j++) {
				Vector2D pnt1 = new Vector2D(i, j);
				if (CollisionDetection.pointInPolygon(pnt1, square))
					Console.log("Point " + pnt1.toString() + " is in poly");
			}

		Polygon ln1 = new Polygon(new Vector2D[] { Vector2D.ZERO,
				new Vector2D(10, 10) });
		Polygon ln2 = new Polygon(new Vector2D[] { new Vector2D(2, 2),
				new Vector2D(2, 4) });
		Console.log("ln1 intersects with ln2: "
				+ (CollisionDetection.linearIntersectionPoint(ln1, ln2)
						.toString() != null));
	}

	/**
	 * Tests the FileManager and prints output.
	 */
	public static void resourceTest() {
		// first, test world loading
		World world = null;
		try {
			// load the first world
			world = FileManager.loadWorld("res/test_world.r2dw");
		} catch (Exception e) {
			Console.error("Exception!", e);
		}
		if (world == null) {
			Console.log("World file is null.");
			return;
		}
		// now, let's try accessing some stuff from our new world
		Console.log("Scene length: " + world.scenes.size());
		Scene scene = world.scenes.get(0);
		Console.log("Scene 0 has name \"" + scene.getName() + "\"");
		Console.log("Player name is " + scene.getValue("Player Name"));
		Console.log("Default enemy strength is "
				+ scene.getValue("Default Enemy Strength"));
		int des = (int) scene.getValue("Default Enemy Strength");
		Console.debug("DES + 1 = "+ ++des);
		Console.debug("Getting resource 0 path: "
				+ world.getResources().get(0).getPath());
		
		// here we begin to test Entity loading
		Entity boss = scene.getEntityList().get(0); // this should be "Boss"
		Console.debug("Getting Entity 0, aka \""+boss.name+"\"");
		Console.debug(boss.name+" has rotation "+boss.rotation);
		
		// test loading a new resource
		R2DResource r = new R2DResource("res/file.jpg");
		Console.log(r.load() ? "Loading file \"" + r.getPath()
				+ "\" was successful." : "Loading file \"" + r.getPath()
				+ "\" failed.");

	}
	
	/**
	 * Incorporates some general test for reflection of Entity objects and all
	 * sub-objects. This way of doing things is to be incorporated into the
	 * loadWorld method of FileManager in a recursive manner, later on.
	 * 
	 * note: This has been single-handedly the most freaking annoying thing to
	 * ever do in Java.
	 */
	public static void entityReflection() {
		Entity en = new Entity("Example Entity"); // hereafter, en is "Entity"
		Console.debug("Created Entity with name "+en.name);
		
		// add some default values first, for testing
		en.position.x = 744;
		
		Field posfield = null; // Entity.position field
		try {
			posfield = en.getClass().getField("position");
			Console.debug("Got field \"position\" in Entity.");
		} catch (NoSuchFieldException | SecurityException e) {
			Console.debug("Could not get field \"position\" in Entity.");
			return;
		}
		
		Object pos_o = null; // object for position (Vector2D); since we're setting
		try {
			pos_o = posfield.get(en); // get the object, position, from Entity
			Console.debug("Instantiated new "+pos_o.getClass().getSimpleName());
			Console.debug("Got pos_o: "+pos_o);
		} catch (Exception e) {
			Console.debug("Could not instantiate.");
			Console.error("Could not instantiate: ", e);
		}
		Console.debug("Object type of \"position\" is "+pos_o.getClass().getSimpleName()+".");
		
		Field pos_xfield = null; // Entity.position.x
		try {
			pos_xfield = pos_o.getClass().getField("x");
			Console.debug("Got field \"x\" in Entity.position.");
		} catch (NoSuchFieldException | SecurityException e) {
			Console.debug("Could not get field \"x\" in Entity.position.");
			return;
		}
		
		try {
			Console.debug("Value of field \"x\" is "+pos_xfield.getFloat(pos_o));
		} catch (Exception e) {
			Console.debug("Could not get value of field \"x\".");
		}
		
		// set x of Entity.position
		try {
			pos_xfield.setFloat(pos_o, 32f); // set our SECOND-level field
			Console.debug("Successfully set field \"x\" of Entity.position.");
		} catch (Exception e) {
			Console.debug("Could not set field \"x\" in Entity.position.");
		}
		
		// set position of Entity
		try {
			posfield.set(en, pos_o); // set our FIRST-level field
			Console.debug("Successfully set field \"position\" of Entity.");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			Console.debug("Could not set field \"position\" in Entity.");
		}
		
		Console.debug("Value of Entity.position.x is now "+en.position.x);
		
		/* This will honestly be forgotten if it is not written down...
		 * Conclusion, steps:
		 * 1. Have Entity object (en).
		 * 2. Create new field, according to name: 
		 * 						field = en.getClass().getField(names[0]);
		 * 3a. Extract object using that field: obj = field.get(en);
		 * 3b. Create ANOTHER field from that object:
		 * 						field2 = obj.getClass().getField(names[1]);
		 * 3c. If you cannot create an object out of field2, then do:
		 * 						field2.set(obj, <YOUR SACRED VALUE>);
		 * 	   Of course, it will be necessary to check which type your field2
		 * 			is, first.
		 * 4a. Now, we have to go BACK and set everything in reverse:
		 * 						field.set(en, obj);
		 */
	}

	public static void main(String[] args) {
		Receiver2D.DEBUG_MODE = true;

		System.out.println(LLevel.DEBUG.shouldPrint(LLevel.UNDEFINED));

		Receiver2D.start(); // initialize the game

		resourceTest(); // test our world loading
		
		//entityReflection(); // test entity reflection

		Receiver2D.stop();
	}
}
