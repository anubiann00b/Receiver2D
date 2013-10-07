package com.receiver2d.engine;

import com.receiver2d.engine.Receiver2D;

public class Console {
	public static void log(String message) {
		System.out.println(Receiver2D.getTimeSinceLaunch()+" "+message);
	}
}
