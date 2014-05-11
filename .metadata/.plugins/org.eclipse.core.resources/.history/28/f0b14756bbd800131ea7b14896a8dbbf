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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONData {
	
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
