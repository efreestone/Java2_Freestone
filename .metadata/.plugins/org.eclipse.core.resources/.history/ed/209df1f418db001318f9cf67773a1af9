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

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.SimpleAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class JSONData.
 */
public class JSONData {
	
	/** The my context. */
	static Context myContext;
	
	/** The my data manager. */
	static DataManager myDataManager;
	
	/** The my file name. */
	static String myFileName = "string_from_url.txt";

	/**
	 * Display data from file.
	 */
	public static void displayDataFromFile() {
		// Grab instance of DataManager
		myDataManager = DataManager.getInstance();

		myContext = MainActivity.myContext;

		String dvdTitle, releaseDate, criticRating;

		String JSONString = DataManager.readStringFromFile(myContext,
				myFileName);

		Log.i("JSONString", JSONString);

		// Create ArrayList with hashmap
		ArrayList<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
		JSONObject jsonObject = null;
		JSONArray newReleaseArray = null;

		try {
			jsonObject = new JSONObject(JSONString);
			newReleaseArray = jsonObject.getJSONArray("movies");
			int arraySize = newReleaseArray.length();
			// testTextView.setText("There are " + String.valueOf(arraySize) +
			// "movies.");

			// Loop through array from file and extract fields
			for (int i = 0; i < arraySize; i++) {

				dvdTitle = newReleaseArray.getJSONObject(i).getString("title");
				// Log.i("displayData newReleaseArray", "Title: " + dvdTitle);
				releaseDate = newReleaseArray.getJSONObject(i)
						.getJSONObject("release_dates").getString("dvd");
				// Log.i("displayData newReleaseArray", "Release Date: " +
				// releaseDate);
				criticRating = newReleaseArray.getJSONObject(i)
						.getJSONObject("ratings").getString("critics_rating");
				// Log.i("displayData newReleaseArray", "Rating: " +
				// criticRating);

				// Instantiate Hash Map for array and pass in strings with
				// key/value pairs
				HashMap<String, String> displayMap = new HashMap<String, String>();
				displayMap.put("dvdTitle", dvdTitle);
				displayMap.put("releaseDate", releaseDate);
				displayMap.put("criticRating", criticRating);

				// Add hash maps to array list
				myList.add(displayMap);
			}

			// Create simple adapter and set up with array
			SimpleAdapter listAdapter = new SimpleAdapter(myContext, myList,
					R.layout.listview_row, new String[] { "dvdTitle",
							"releaseDate", "criticRating" },
					new int[] { R.id.dvdTitle, R.id.releaseDate,
							R.id.criticRating });

			MainActivity.myListView.setAdapter(listAdapter);

		} catch (JSONException e) {
			Log.e("displayDataFromFile", e.getMessage().toString());
		}
	}

}
