package com.receiver2d.engine.tests;

//import static org.junit.Assert.*; //for assertEquals() and such
import org.junit.Test;

import com.receiver2d.engine.Receiver2D;
import com.receiver2d.engine.Receiver2D.DebugMode;

public class MainTest {
	@Test
	public void test() {
		Receiver2D.DEBUG_MODE = DebugMode.DEBUG_R2D_AND_LWJGL;
		Receiver2D.start();
		Receiver2D.stop();
	}
}
