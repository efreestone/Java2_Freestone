/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project1
 * 
 * Package	com.elijahfreestone.networkConnection
 * 
 * date		May 6, 2014
 */

package com.elijahfreestone.networkConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.elijahfreestone.java2project1.DataService;
import com.elijahfreestone.java2project1.MainActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


/**
 * The Class JSONData handles network connection testing and parsing and object creation of JSON Data from the API call that takes place
 * in DataService.
 */
public class JSONData {
	static String responseString;
	
	/**
	 * Connection status.
	 *
	 * @param myContext the my context
	 * @return the boolean
	 */
	public static Boolean connectionStatus(Context myContext) {
		Boolean connectionBool = false;

		ConnectivityManager connectionManager = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
		
		if (networkInfo != null) {
			if (networkInfo.isConnected()) {
				connectionBool = true;
			}
		}
		return connectionBool;
	} //connectionStatus Close
	
	public static String readJSON(String responseString2) throws JSONException {
		String results, dvdTitle, releaseDate, criticRating, audienceRating, objectInfo;
		StringBuffer newReleaseBuffer;
		
		JSONObject newReleaseObject = new JSONObject(responseString2);
		
		JSONArray newReleaseArray = newReleaseObject.getJSONArray("movies");
		
		for (int i = 0; i < newReleaseArray.length(); i++) {
			dvdTitle = newReleaseArray.getJSONObject(i).getString("title");
			Log.i("newReleaseArray", "Title: " + dvdTitle);
			releaseDate = newReleaseArray.getJSONObject(i).getJSONObject("release_dates").getString("dvd");
			Log.i("newReleaseArray", "Release Date: " + releaseDate);
		}
		
		
		objectInfo = "";
		newReleaseBuffer = new StringBuffer();
		
		//JSONArray newReleaseArray = newReleaseObject.get("movies").
		
		
		
		return null;
	} //readJSON Close
	
}