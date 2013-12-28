package com.receiver2d.engine.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	/**
	 * Creates a new resource to be allocated by the engine.
	 * @param loc The location of the resource in the file system.
	 * @param type The presupposed mime-type of the resource.
	 */
	public R2DResource(String loc, String type) {
		setResource(loc, type);
	}

	/**
	 * Creates a new resource to be allocated by the engine.
	 * @param loc The location of the resource in the file system.
	 */
	public R2DResource(String loc) {
		setResource(loc);
	}

	/**
	 * Changes the current resource to a different one.
	 * 
	 * @param loc 	The location (in the filesystem) of the resource.
	 * @param type	The mime-type of the resource.
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
		} else Console.log("File " + loc + " either doesn't exist or is not a file.");
		return false;
	}

	/**
	 * Changes the current resource to a different one.
	 * 
	 * @param loc 	The location (in the filesystem) of the resource.
	 * @return Whether or not the operation was successful in changing the
	 *         resource. This is affected by whether or not the file exists in
	 *         the system. If not, the resource remains unchanged.
	 */
	public boolean setResource(String loc) {
		try {
			setResource(loc, Files.probeContentType(Paths.get(loc)));
			return true;
		} catch (IOException e) {
			Console.error("File " + loc + " mime type cannot be determined.", e);
		}
		return false;
	}

	/**
	 * Loads a file into memory from the resource's current path. This must be
	 * called before calling getLoaded().
	 * 
	 * @return true if the operation was successful, false if otherwise
	 */
	public boolean load() {
		try {
			ioStream = new FileInputStream(loadedFile = new File(location));
			return true;
		} catch (Exception e) {
			Console.error("Cannot load file which does not exist.", e);
		}
		return false;
	}

	/**
	 * Clears the file from memory by setting it to null. Does not remove the
	 * location variable of the resource, though.
	 */
	public void unload() {
		try {
			if (ioStream != null) ioStream.close(); // close the stream first
		} catch (IOException e) {
			Console.error("Error when closing file ioStream.", e);
		}
		ioStream = null;
		loadedFile = null;
	}

	/**
	 * Gets the loaded file from the resource's location, or null if undefined.
	 * If the file is not loaded prior by calling .load() then it is loaded
	 * automatically.
	 * @return The file of the loaded resource.
	 */
	public File getFile() {
		load(); // load the file automatically
		return loadedFile;
	}

	/**
	 * Gets the path of the resource.
	 * 
	 * @return The path of the resource.
	 */
	public String getPath() {
		return location;
	}

	/**
	 * Gets the mime type of the resource.
	 * 
	 * @return The mime type of the file, or null if undefined.
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Gets the input stream of the currently loaded resource.
	 * 
	 * @return The input stream, or null if the file is not loaded.
	 */
	public FileInputStream getInputStream() {
		return ioStream;
	}
}
