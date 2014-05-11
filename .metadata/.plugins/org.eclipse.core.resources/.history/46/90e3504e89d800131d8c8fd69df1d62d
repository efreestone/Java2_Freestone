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
	
	//
	public Boolean writeStringToFile (Context context, String fileName, String content) {
		Boolean result = false;
		
		//Create and open file output stream
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fileOutputStream.write(content.getBytes());
			Log.i("writeStringToFile", "Write Successful!");
		} catch (FileNotFoundException e) {
			Log.e("writeStringToFile", e.getMessage().toString());
		} catch (IOException e) {
			Log.e("writeStringToFile", e.getMessage().toString());
		}
		
		return result;
	}

}
