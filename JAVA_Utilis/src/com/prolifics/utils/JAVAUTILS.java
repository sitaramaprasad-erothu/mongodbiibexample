package com.prolifics.utils;

import java.io.File;

public class JAVAUTILS {

	/**
	 * @param args
	 */
	public static Boolean checkIfFileExists(String filePath) {
		// TODO Auto-generated method stub
		File tempFile = new File(filePath);
		boolean exists = tempFile.exists();
		
		return exists;

	}

}
