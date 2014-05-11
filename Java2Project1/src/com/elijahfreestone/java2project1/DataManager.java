/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project1
 * 
 * Package	com.elijahfreestone.java2project1
 * 
 * date		May 10, 2014
 */
package com.elijahfreestone.java2project1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class DataManager {
	
	//Create instance of Singleton
	private static DataManager dataManagerInstance;
	
	//Create Constructor
	private DataManager() {
		
	}
	
	//Create getInstance method and check if instance exists
	public static DataManager getInstance() {
		if (dataManagerInstance == null) {
			dataManagerInstance = new DataManager();
			
		}
		return dataManagerInstance;
	}
	
	//Create writeStringToFile. Is called from onClick in the Main Activity and saves file to the device
	public Boolean writeStringToFile (Context context, String fileName, String content) {
		Boolean result = false;
		
		//Create and open file output stream
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fileOutputStream.write(content.getBytes());
			Log.i("writeStringToFile", "Write Successful!");
			MainActivity.myFileName = fileName;
		} catch (FileNotFoundException e) {
			Log.e("writeStringToFile", e.getMessage().toString());
		} catch (IOException e) {
			Log.e("writeStringToFile", e.getMessage().toString());
		}
		
		
		return result;
	} //writeStringToFile Close
	
	//Method to read JSON file from the device. The string created is passed to be parsed and displayed
	public static String readStringFromFile (Context context, String fileName) {
		String fileContent = "";
		
		//Create and open file input stream
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = context.openFileInput(fileName);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] contentBytes = new byte[1024];
			int bytesRead = 0;
			StringBuffer contentBuffer = new StringBuffer();
			//Loop through file and read
			while ((bytesRead = bufferedInputStream.read(contentBytes)) != -1) {
				fileContent = new String(contentBytes, 0, bytesRead);
				//Append data to the content buffer
				contentBuffer.append(fileContent);
			}
			//Pass/cast contentBuffer to my fileContent string
			fileContent = contentBuffer.toString();
		} catch (Exception e) {
			Log.e("readStringToFile", e.getMessage().toString());
		} finally { //closes file no matter what
			try {
				fileInputStream.close();
			} catch (IOException e) {
				Log.e("readStringToFile", e.getMessage().toString());
			}
		}
		
		return fileContent;
	} //readStringFromFile Close

}
