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

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.elijahfreestone.networkConnection.NetworkConnection;


//TODO: Auto-generated Javadoc
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
	static String filePathDir = "data/data/com.elijahfreestone.java2project1/files/string_from_url.txt";
	static TextView testTextView;
	
	static final String DVD_TITLE = "dvdTitle";
	static final String RELEASE_DATE = "releaseDate";
	static final String MOVIE_RATING = "movieRating";
	
	static Boolean fileFlag;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Instantiate the list view and add header
		myListView = (ListView) this.findViewById(R.id.listView);
		View listHeader = this.getLayoutInflater().inflate(
				R.layout.listview_header, null);
		myListView.addHeaderView(listHeader);

		// Grab instance of DataManager
		myDataManager = DataManager.getInstance();

		myContext = this;

		//Check if file exists on the device and alert the user if it doesn't
		String fileDirectory = android.os.Environment.getRootDirectory() + filePathDir;
		//Log.i("File", fileDirectory);
		File file = new File(fileDirectory);
		//File testFile = new File(myFileName);
		
		//File file = myContext.getFileStreamPath(myFileName);
		
		//fileFlag = false;
		
/*------Always returning false as of now!!------*/
		if (file.isFile() && !file.isDirectory()) {
			// Display the data to the listview automatically
			JSONData.displayDataFromFile();
			
			Log.i("File", "File exists");
		} else {
			if (NetworkConnection.connectionStatus(myContext)) {
				// Show No File alert
				noFileAlert();
				// Call retrieveData to start the Service and get JSON data from the API
				retrieveData();
			} else {
				// Show No Connection alert
				noConnectionAlert();
			}

			
			Log.i("File", "File DOESN'T exist!!");
		}

		//Grab button by id and set onClick with network connection check
		Button newReleaseButton = (Button) findViewById(R.id.newReleaseButton);
		newReleaseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// System.out.println("Button Clicked");

				if (NetworkConnection.connectionStatus(myContext)) {
					// System.out.println("Network Works!!");
					Log.i(TAG, "Network Works!!");

					JSONData.displayDataFromFile();
				} else {
					// Show no connection alert
					noConnectionAlert();

					Log.i(TAG, "No Network!!");
				}
			} // onClick Close
		}); // onClickListener Close
		
		//Create onItemClickListener for the listview. This grabs details of the object selected,
		//creates new intent, and passes object details to Deatils Activty for display
		myListView.setOnItemClickListener(new OnItemClickListener() {
			HashMap<String, String> selectedMovie;
			String dvdTitle, releaseDate, movieRating, criticRating, audienceRating;

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectedMovie = JSONData.myList.get(position-1);
				dvdTitle = selectedMovie.get("dvdTitle");
				releaseDate = selectedMovie.get("releaseDate");
				movieRating = selectedMovie.get("movieRating");
				criticRating = selectedMovie.get("criticRating");
				audienceRating = selectedMovie.get("audienceRating");
				Log.i("File Selected", dvdTitle);
				
				Toast.makeText(getApplicationContext(), "DVD Title: " + selectedMovie, Toast.LENGTH_LONG).show();
				
				Log.i("File Selected", "" + selectedMovie);
				
				//Create explicit intent and pass dvd details as extras
				Intent detailsIntent = new Intent(myContext, DetailsActivity.class);
				detailsIntent.putExtra("dvdTitle", dvdTitle);
				detailsIntent.putExtra("releaseDate", releaseDate);
				detailsIntent.putExtra("movieRating", movieRating);
				detailsIntent.putExtra("criticRating", criticRating);
				detailsIntent.putExtra("audienceRating", audienceRating);
				
				startActivityForResult(detailsIntent, 0);
			}
		});

	} // onCreate Close
	

	/**
	 * The Class MyServiceHandler creates a Service Handler with a Weak Ref to the MainActivty. This is
	 * done to avoid the memory leak 
	 */
	private static class MyServiceHandler extends Handler {
		private final WeakReference<MainActivity> myActivity;
		//Instantiate the handler
		public MyServiceHandler(MainActivity activity) {
			myActivity = new WeakReference<MainActivity>(activity);
		}

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
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
					// Write the file to the system
					//myDataManager.writeStringToFile(myContext, myFileName, DataService.responseString);

				} else {
					Log.i(TAG, "Data NOT created!!");
				}
			}

		} // HandleMessage Close
	} // MyHandler Close

	/**
	 * 
	 * Retrieve data.
	 */
	public void retrieveData() {
		Log.i(TAG, "retrieveData called");
		// Create Messenger class for ref to handler
		Messenger dataMessenger = new Messenger(myServiceHandler);

		// Create Intent to start service
		Intent startDataIntent = new Intent(myContext, DataService.class);
		startDataIntent.putExtra(DataService.MESSENGER_KEY, dataMessenger);

		// Start the service
		startService(startDataIntent);
	} // retrieveData Close

	// Custom methods to show no connection or no file alerts
	public void noConnectionAlert() {
		// Create alert dialog for no connection
		AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
		alertDialog.setTitle(R.string.noConnectionTitle);
		// Set alert message. setMessage only has a charSequence
		// version so getString must be used.
		alertDialog.setMessage(myContext.getString(R.string.noConnectionAlert));
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
		alertDialog.show();
	}

	public void noFileAlert() {
		// Create alert dialog for no file
		AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
		alertDialog.setTitle(R.string.noFileTitle);
		// Set alert message. setMessage only has a charSequence
		// version so getString must be used.
		alertDialog.setMessage(myContext.getString(R.string.noFileAlert));
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
		alertDialog.show();
	} //Alert methods Close
	
//	public Boolean doesFileExist(String filePath) {
//		try {
//			File file = new File(filePathDir);
//			Log.i("File", "Bool File Exists");
//			//fileFlag = true;
//			Log.i("File", "fileFlag in Bool = " + fileFlag);
//			return file.exists();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			Log.i("File", "Bool File DOESN'T Exist!!");
//		}
//		
//		return false;
//		
//	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		
	}
	
	public void goToDetailsActivity() {
		Intent detailsIntent = new Intent(myContext, DetailsActivity.class);
		startActivity(detailsIntent);
	}
	
//	@Override
//	public void startActivityForResult(Intent intent, int requestCode) {
//		
//		super.startActivityForResult(intent, requestCode);
//	}
}