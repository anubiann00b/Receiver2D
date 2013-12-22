package com.receiver2d.engine.tests;

//import static org.junit.Assert.*; //for assertEquals() and such
import org.junit.Test;

import com.receiver2d.engine.Receiver2D;

public class MainTest {
	@Test
	public void test() {
		Receiver2D.DEBUG_MODE = true;

		Receiver2D.start();
		Receiver2D.stop();
	}

}
