package com.receiver2d.engine;

import java.io.File;

/**
 * Useful for loading scene resources dynamically from the local filesystem.
 */
public class SceneResource {
	private String loc;
	private String mimeType;
	private File loadedFile = null;
	public SceneResource(String location, String mimeType) {
		loc = location;
		this.mimeType = mimeType;
	}
	
	/**
	 * Changes the current resource to a different one.
	 * 
	 * @param location
	 *            The location (in the filesystem) of the resource.
	 * @param mimeType
	 *            The mimetype of the resource.
	 * @return Whether or not the operation was successful in changing the
	 *         resource. This is affected by whether or not the file exists in
	 *         the system. If not, the resource remains unchanged.
	 */
	public boolean setResource(String location, String mimeType) {
		File file = new File(location);
		if (file.exists() && file.isFile()) {
			loc = location;
			this.mimeType = mimeType;
			return true;
		}
		return false;
	}
	/**
	 * Loads a file into memory from the resource's current path. This must be
	 * called before calling getLoaded().
	 */
	public void load() {
		loadedFile = new File(loc);
	}
	/**
	 * Gets the loaded file from the resource's location, or null if undefined.
	 * @return The file of the loaded resource.
	 */
	public File getLoaded() {
		return loadedFile;
	}
}
