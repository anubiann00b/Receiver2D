package com.receiver2d.editor;

import java.awt.FileDialog;

import javax.swing.JFrame; // for fileIO

import com.receiver2d.editor.gui.FileIO;

/**
 * This is the editor. It should not be imported into projects with the engine,
 * but should be used as a standalone map editor.
 */

public class Editor {
	public static void main(String[] args) {
		FileIO.fd = new FileDialog(new JFrame());
		System.out.println(FileIO.requestFile().getPath());
		System.out.println(System.getProperty("os.name") + " "
				+ System.getProperty("os.arch") + " "
				+ System.getProperty("os.version"));
		System.exit(0);
	}
}
