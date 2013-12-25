package com.receiver2d.engine.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.receiver2d.engine.Console;
import com.receiver2d.engine.Scene;
import com.receiver2d.engine.World;
import com.receiver2d.engine.entitysystem.Entity;
import com.receiver2d.engine.io.FileManager;
import com.receiver2d.engine.io.R2DResource;

public class IOTest {

	@Before
	public void setUp() throws Exception {
		Console.level = Console.LogLevel.DEBUG;
	}

//	@Test //will not be implemented until FileManager.load() is broken up and throws exceptions
	public void testLoadRsrc(){
		Console.level = Console.LogLevel.DEBUG;
		
		World world = null;
		try {
			world = FileManager.loadWorld("res/test_world.r2dw");
		} catch (Exception e) {
			Console.error("Exception caught while loading test_world.r2dw", e);
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

		// test texture loading
		//Texture2D ishygddt = new Texture2D(r, null);
	}
	
}
