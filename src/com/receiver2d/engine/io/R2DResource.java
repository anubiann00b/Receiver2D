package com.receiver2d.engine.io;

import java.io.*;
import java.nio.file.*;

import com.receiver2d.engine.Console;

/**
 * Useful for loading scene resources dynamically from the local filesystem.
 */
public class R2DResource {
	private String location = null;
	private String mimeType = null;
	
	/**
	 * The conventional mime-type of a Receiver2D World File (.r2dw) that we
	 * assume.
	 */
	public static final String R2DW_MIMETYPE = "application/vnd.r2dworld+xml";
	
	private File loadedFile = null;
	private FileInputStream ioStream = null;
	
	public R2DResource(String loc, String type) {
		setResource(loc, type);
	}
	
	public R2DResource(String loc) {
		setResource(loc);
	}

	/**
	 * Changes the current resource to a different one.
	 * 
	 * @param loc The location (in the filesystem) of the resource.
	 * @param type The mime-type of the resource.
	 * @return Whether or not the operation was successful in changing the
	 *         resource. This is affected by whether or not the file exists in
	 *         the system. If not, the resource remains unchanged.
	 */
	public boolean setResource(String loc, String type) {
		File file = new File(loc);
		if (file.exists() && file.isFile()) {
			location = loc;
			mimeType = type;
			return true;
		} else 
			Console.debug("File "+loc+" either doesn't exist or is not a file.");
		return false;
	}
	
	public boolean setResource(String loc) {
		try {
			setResource(loc, Files.probeContentType(Paths.get(loc)));
			return true;
		} catch (IOException e) {
			Console.logError("File "+loc+" mime type cannot be determined.", e);
		}
		return false;
	}

	/**
	 * Loads a file into memory from the resource's current path. This must be
	 * called before calling getLoaded().
	 */
	public void load() {
		try {
			ioStream = new FileInputStream(loadedFile = new File(location));
		} catch (Exception e) { }
	}
	
	/**
	 * Clears the file from memory by setting it to null. Does not remove the
	 * location variable of the resource, though.
	 */
	public void unLoad() {
		try {
			if (ioStream != null)
				ioStream.close(); // close the stream before nullifying
		} catch (IOException e) { }
		ioStream = null;
		loadedFile = null;
	}

	/**
	 * Gets the loaded file from the resource's location, or null if undefined.
	 *
	 * @return The file of the loaded resource.
	 */
	public File getFile() {
		return loadedFile;
	}
	
	/**
	 * Gets the mime type of the resource.
	 * @return The mime type of the file, or null if undefined.
	 */
	public String getMimeType() {
		return mimeType;
	}
	
	/**
	 * Gets the input stream of the currently loaded resource.
	 * @return The input stream, or null if the file is not loaded.
	 */
	public FileInputStream getInputStream() {
		return ioStream;
	}
}
