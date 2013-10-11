package com.receiver2d.editor;

import com.receiver2d.editor.gui.FileIO;
// for fileIO

/**
 * This is the editor. It should not be imported into projects with the engine,
 * but should be used as a stand-alone map editor.
 */

public class Editor {

	public static void main(String[] args) {
		// FileIO.fd = new FileDialog(new JFrame());
		System.out.println(FileIO.requestFile().getPath());
		System.out.println(System.getProperty("os.name") + " "
				+ System.getProperty("os.arch") + " "
				+ System.getProperty("os.version"));
		System.exit(0);
	}
}
