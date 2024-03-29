/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project1
 * 
 * Package	com.elijahfreestone.java2project1
 * 
 * date		May 9, 2014
 */

package com.elijahfreestone.java2project1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class DataService extends IntentService {
	static String TAG = "DataService";
	
	public static final String MESSENGER_KEY = "messenger";
	public static final String TIME_KEY = "time";
	
	public static String responseString;
	
	String urlString = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=xxnu68qnz6ap2ygf4qvu659t&page_limit=10";

	public DataService() {
		super("DataService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//TAG = "onHandleIntent";
		
		responseString = "";
		
		Log.i(TAG, "handle intent started");
		
		Bundle extras = intent.getExtras();
		Messenger messenger = (Messenger) extras.get(MESSENGER_KEY);
		
		try {
			URL url = new URL(urlString);
			responseString = getResponse(url);
			
			Log.i(TAG, "Response String");
			//Call readJSON on JSONData and pass responseString
			//JSONData.readJSON(responseString);
			Log.i(TAG, "readJSON called");
		} catch (MalformedURLException e1) {
			responseString = "something went wrong";
			Log.e(TAG, e1.getMessage().toString());
		}
			
		Message message = Message.obtain();
		message.arg1 = Activity.RESULT_OK;
		message.obj = "Service is done";
		
		try {
			messenger.send(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage().toString());
		}	
	} //onHandleIntent Close
	
	// Method to get response from url
	public static String getResponse(URL url) {
		// Create string for response
		String response = "";
		
		Log.i(TAG, "Get Response called");

		try {
			// Open connection
			URLConnection connection = url.openConnection();
			// Create buffered input stream
			BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
			// Create byte and set to 1024
			byte[] contextByte = new byte[1024];
			// Create int for bytes read
			int byteRead = 0;
			// Create string buffer for adding response to
			StringBuffer responseBuffer = new StringBuffer();
			// While loop
			while ((byteRead = bufferedInputStream.read(contextByte)) != -1) {
				// Set response string to receive bytes
				response = new String(contextByte, 0, byteRead);
				// Append response to response buffer
				responseBuffer.append(response);
			}

			// Fill response string with response buffer once while loopcompletes
			response = responseBuffer.toString();
			// System.out.println(response);
			// Log response
			Log.i(TAG, response);
		} catch (IOException e) {
			// Set response to error
			response = "URL broken";
			// e.printStackTrace();
			// Log error
			Log.e(TAG, "Something went wrong", e);
		}
		// Return response string
		return response;
	} //getResponse Close

}
