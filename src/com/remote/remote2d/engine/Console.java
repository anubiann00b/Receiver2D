package com.remote.remote2d.engine;

import com.remote.remote2d.engine.Receiver2D;

public class Console {
	public static void log(String message) {
		System.out.println(Receiver2D.getTimeSinceLaunch()+" "+message);
	}
}
