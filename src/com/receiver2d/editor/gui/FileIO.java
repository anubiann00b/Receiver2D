package com.receiver2d.editor.gui;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Implements basic input and output functions for the Editor, in managing
 * local files.
 */
public class FileIO {
	public static FileDialog fd;

	/**
	 * Requests a single file from the user.
	 * @return the requested file or null if user presses 'cancel'
	 */
	public static File requestFile() {
		setDefaultUI();
		fd = new FileDialog(new JFrame());
		fd.setMultipleMode(false);
		fd.setVisible(true);
		return fd.getFiles()[0];
	}

	/**
	 * Requests multiple files from the user.
	 * 
	 * @return the requested files or null if user presses 'cancel'
	 */
	public static File[] requestFiles() {
		setDefaultUI();
		fd.setMultipleMode(true); // allow multiple files
		fd.setVisible(true);
		return fd.getFiles();
	}

	/**
	 * Prompt the user to save a file
	 * 
	 * @param contents
	 *            the Object array to save
	 * @return true for success, false for failure or cancel
	 */
	// *********************
	// INCOMPLETE DO NOT USE
	// *********************
	public static boolean requestSaveFile(Object[] contents,
			String defaultFileName) {
		setDefaultUI();
		fd.setMode(FileDialog.SAVE);
		fd.setFile(defaultFileName);
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(new File(fd.getFile())));
			for (Object o : contents)
				out.writeObject(o);
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Attempts to set the appearance of the file chooser to the default
	 * requested by the system's theme.
	 */
	public static void setDefaultUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			// print out the error, but continue since default UI still works
		}
	}
}
