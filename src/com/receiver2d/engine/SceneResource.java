package com.receiver2d.engine;

import java.io.File;

/**
 * Useful for loading scene resources dynamically from the local filesystem.
 */
public class SceneResource {
	private String location;
	public static final String mimetype = "application/vnd.r2dscene+xml";
	private File loadedFile = null;

	public SceneResource(String location) {
		this.location = location;
	}

	/**
	 * Changes the current resource to a different one.
	 * 
	 * @param location
	 *            The location (in the filesystem) of the resource.
	 * @return Whether or not the operation was successful in changing the resource. This is affected by whether or not the file exists in the system. If not, the resource remains unchanged.
	 */
	public boolean setResource(String location) {
		File file = new File(location);
		if (file.exists() && file.isFile()) {
			this.location = location;
			return true;
		}
		return false;
	}

	/**
	 * Loads a file into memory from the resource's current path. This must be called before calling getLoaded().
	 */
	public void load() {
		loadedFile = new File(location);
	}

	/**
	 * Gets the loaded file from the resource's location, or null if undefined.
	 * 
	 * @return The file of the loaded resource.
	 */
	public File getLoaded() {
		return loadedFile;
	}
}
