package com.remote.remote2d.engine;

import com.remote.remote2d.engine.Remote2D;

public class Console {
	public static void log(String message) {
		System.out.println(Remote2D.getTimeSinceLaunch()+" "+message);
	}
}
