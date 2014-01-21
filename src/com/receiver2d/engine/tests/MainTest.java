package com.receiver2d.engine.tests;

//import static org.junit.Assert.*; //for assertEquals() and such
import org.junit.Test;

import com.receiver2d.engine.Console;
import com.receiver2d.engine.Receiver2D;
import com.receiver2d.engine.tests.IOTest;
import java.io.*;

public class MainTest {
	@Test
	public static void main(String[] args) throws IOException {
		Console.CURRENT_DEBUG_MODE = Console.DebugMode.DEBUG_R2D;
		// test loading
		IOTest.testLoadRsrc();
		Receiver2D.start();
		Receiver2D.stop();
	}
}
