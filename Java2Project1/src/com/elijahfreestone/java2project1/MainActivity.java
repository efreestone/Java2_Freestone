/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project1
 * 
 * Package	com.elijahfreestone.java2project1
 * 
 * date		May 6, 2014
 */

package com.elijahfreestone.java2project1;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.elijahfreestone.networkConnection.NetworkConnection;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {
	static Context myContext;
	static String TAG = "MainActivity";
	static String responseString = null;
	static ListView myListView;
	final MyServiceHandler myServiceHandler = new MyServiceHandler(this);
	static DataManager myDataManager;
	static String myFileName = "string_from_url.txt";
	
	String testString = "10";
	
	static TextView testTextView;
	
	
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Instantiate the list view and add header
        myListView = (ListView) this.findViewById(R.id.listView);
        View listHeader = this.getLayoutInflater().inflate(R.layout.listview_header, null);
        myListView.addHeaderView(listHeader);
        
        //Grab instance of DataManager
        myDataManager = DataManager.getInstance();
        
        myContext = this;
        
        Button newReleaseButton = (Button) findViewById(R.id.newReleaseButton);
        final TextView testTextView = (TextView) findViewById(R.id.test_textview);
        
        //Call retrieveData to start the Service and get JSON data from the API
        retrieveData();
		
		testTextView.setText("Waiting");
        
		newReleaseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//System.out.println("Button Clicked");
				
				if (NetworkConnection.connectionStatus(myContext)) {
					//System.out.println("Network Works!!");
					Log.i(TAG, "Network Works!!");
//					String
//					
//					if (condition) {
//						
//					}
					
					myDataManager.writeStringToFile(myContext, myFileName, DataService.responseString);
					
				} else {
					// Create alert dialog for no connection
					AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
					alertDialog.setTitle(R.string.noConnectionTitle);
					// Set alert message. setMessage only has a charSequence
					// version so getString must be used.
					alertDialog.setMessage(myContext.getString(R.string.noConnectionAlert));
					alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
					alertDialog.show();
					
					Log.i(TAG, "No Network!!");
				}
			} //onClick Close
		}); //onClickListener Close
		
    } //onCreate Close
    
    private static class MyServiceHandler extends Handler {
    	
    	private final WeakReference<MainActivity> myActivity;
    	
    	public MyServiceHandler(MainActivity activity) {
    		myActivity = new WeakReference<MainActivity>(activity);
    	}
    	
    	@Override
    	public void handleMessage(Message message) {
    		
    		MainActivity activity = myActivity.get();
    		if (activity != null) {
				Object returnObject = message.obj;
				if (message.arg1 == RESULT_OK && returnObject != null) {
					try {
						responseString = (String) message.obj;
						Log.i(TAG, "handleMessage " + responseString);
					} catch (Exception e) {
						Log.e("handleMessage", e.getMessage().toString());
					}
//***********************Display data from file here***********************//
					displayDataFromFile();
					//testTextView.setText(responseString);
					
					
				} else {
					Log.i(TAG, "Data NOT created!!");
				}
			}
    		
    	} //HandleMessage Close
    } //MyHandler Close
    
    public void retrieveData() {
    	Log.i(TAG, "retrieveData called");
    	//Create Messenger class for ref to handler
    	Messenger dataMessenger = new Messenger(myServiceHandler);
    			
    	//Create Intent to start service
    	Intent startDataIntent = new Intent(myContext, DataService.class);
    	startDataIntent.putExtra(DataService.MESSENGER_KEY, dataMessenger);
    			
    	//Start the service
    	startService(startDataIntent);
    	
    } //retrieveData Close
    
    public static void displayDataFromFile() {
    	String results, dvdTitle, releaseDate, criticRating, audienceRating, objectInfo;
    	
    	String JSONString = myDataManager.readStringFromFile(myContext, myFileName);
    	
    	Log.i("JSONString", JSONString);
    	
    	//Create ArrayList with hashmap
    	ArrayList<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
    	JSONObject jsonObject = null;
    	JSONArray newReleaseArray =  null;
    	
    	
    	try {
			jsonObject = new JSONObject(JSONString);
			newReleaseArray = jsonObject.getJSONArray("movies");
			int arraySize = newReleaseArray.length();
			//testTextView.setText("There are " + String.valueOf(arraySize) + "movies.");
			
			//Loop through array from file and extract fields
			for (int i = 0; i < arraySize; i++) {
				
				dvdTitle = newReleaseArray.getJSONObject(i).getString("title");
				//Log.i("displayData newReleaseArray", "Title: " + dvdTitle);
				releaseDate = newReleaseArray.getJSONObject(i).getJSONObject("release_dates").getString("dvd");
				//Log.i("displayData newReleaseArray", "Release Date: " + releaseDate);
				criticRating = newReleaseArray.getJSONObject(i).getJSONObject("ratings").getString("critics_rating");
				//Log.i("displayData newReleaseArray", "Rating: " + criticRating);
				
				//Instantiate Hash Map for array and pass in strings with key/value pairs
				HashMap<String, String> displayMap = new HashMap<String, String>();
				displayMap.put("dvdTitle", dvdTitle);
				displayMap.put("releaseDate", releaseDate);
				displayMap.put("criticRating", criticRating);
				
				//Add hash maps to array list
				myList.add(displayMap);
			}
			
			//Create simple adapter and set up with array
			SimpleAdapter listAdapter = new SimpleAdapter(myContext, myList, R.layout.listview_row, 
					new String[] {"dvdTitle",  "releaseDate", "criticRating"}, new int[] {R.id.dvdTitle, R.id.releaseDate, R.id.criticRating});
			
			myListView.setAdapter(listAdapter);
			
		} catch (JSONException e) {
			Log.e("displayDataFromFile", e.getMessage().toString());
		}
    }
    
}
