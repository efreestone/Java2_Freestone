/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project2
 * 
 * Package	com.elijahfreestone.java2project2
 * 
 * date		May 12, 2014
 */

package com.elijahfreestone.java2project2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class DataManager.
 */
public class DataManager {

	// Create instance of Singleton
	/** The data manager instance. */
	private static DataManager dataManagerInstance;

	// Create Constructor
	/**
	 * Instantiates a new data manager.
	 */
	private DataManager() {

	}

	// Create getInstance method and check if instance exists
	/**
	 * Gets the single instance of DataManager.
	 *
	 * @return single instance of DataManager
	 */
	public static DataManager getInstance() {
		if (dataManagerInstance == null) {
			dataManagerInstance = new DataManager();

		}
		return dataManagerInstance;
	}

	// Create writeStringToFile. Is called from onClick in the Main Activity and
	// saves file to the device
	/**
	 * Write string to file.
	 *
	 * @param context the context
	 * @param fileName the file name
	 * @param content the content
	 * @return the boolean
	 */
	public Boolean writeStringToFile(Context context, String fileName,
			String content) {
		Boolean result = false;

		// Create and open file output stream
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			fileOutputStream.write(content.getBytes());
			Log.i("writeStringToFile", "Write Successful!");
			MainActivity.myFileName = fileName;
		} catch (FileNotFoundException e) {
			Log.e("writeStringToFile", e.getMessage().toString());
		} catch (IOException e) {
			Log.e("writeStringToFile", e.getMessage().toString());
		}

		return result;
	} // writeStringToFile Close

	// Method to read JSON file from the device. The string created is passed to
	// be parsed and displayed
	/**
	 * Read string from file.
	 *
	 * @param context the context
	 * @param fileName the file name
	 * @return the string
	 */
	public static String readStringFromFile(Context context, String fileName) {
		String fileContent = "";

		// Create and open file input stream
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = context.openFileInput(fileName);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					fileInputStream);
			byte[] contentBytes = new byte[1024];
			int bytesRead = 0;
			StringBuffer contentBuffer = new StringBuffer();
			// Loop through file and read
			while ((bytesRead = bufferedInputStream.read(contentBytes)) != -1) {
				fileContent = new String(contentBytes, 0, bytesRead);
				// Append data to the content buffer
				contentBuffer.append(fileContent);
			}
			// Pass/cast contentBuffer to my fileContent string
			fileContent = contentBuffer.toString();
		} catch (Exception e) {
			Log.e("readStringToFile", e.getMessage().toString());
		} finally { // closes file no matter what
			try {
				fileInputStream.close();
			} catch (IOException e) {
				Log.e("readStringToFile", e.getMessage().toString());
			}
		}

		return fileContent;
	} // readStringFromFile Close

}
