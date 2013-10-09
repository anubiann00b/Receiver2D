package com.receiver2d.editor.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileIO {
	static JFileChooser jfc = new JFileChooser();

	/**
	 * Requests a single file from the user.
	 * @return the requested file or null if user presses 'cancel'
	 */
	public static File requestFile() {
		setDefaultUI();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setMultiSelectionEnabled(false); // allow one file
		int ret = jfc.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION)
			return jfc.getSelectedFile();
		else
			return null;
	}

	/**
	 * Requests multiple files from the user.
	 * 
	 * @return the requested files or null if user presses 'cancel'
	 */
	public static File[] requestFiles() {
		setDefaultUI();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setMultiSelectionEnabled(true); // allow multiple files
		int ret = jfc.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION)
			return jfc.getSelectedFiles();
		else
			return null;
	}

	/**
	 * Request a file or folder from the user.
	 * 
	 * @return The selected file or folder, or null if user presses 'cancel'
	 */
	public static File requestFileOrDir() {
		setDefaultUI();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.setMultiSelectionEnabled(false);
		int ret = jfc.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION)
			return jfc.getSelectedFile();
		else
			return null;
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
	public static boolean requestSaveFile(Object[] contents, String defaultFileName) {
		setDefaultUI();
		int ret = jfc.showSaveDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION){
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(jfc.getSelectedFile()));
				for(Object o : contents)
					out.writeObject(o);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else
			return false;
	}

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
