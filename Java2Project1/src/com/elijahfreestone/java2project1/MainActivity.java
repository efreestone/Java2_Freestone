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

import java.io.File;
import java.lang.ref.WeakReference;

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
	static String filePathDir = "com.elijahfreestone.java2project1/file/string_from_url.txt";
	
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
        
        //File file = new File(this.getFilesDir().getAbsoluteFile() + filePathDir);
        String fileDirectory = android.os.Environment.getRootDirectory() + filePathDir;
        Log.i("File", fileDirectory);
        File file = new File(filePathDir);
        if (file.exists()) {
        	//Display the data to the listview automatically
        	JSONData.displayDataFromFile();
        	Log.i("File", "File exists");
        } else {
        	
        	if (NetworkConnection.connectionStatus(myContext)) {
        		//Show No File alert
        		noFileAlert();
        		//Call retrieveData to start the Service and get JSON data from the API
                retrieveData();
			} else {
				//Show No Connection alert
				noConnectionAlert();
			}
        	
        	Log.i("File", "File DOESN'T exist!!");
        } 
		
		//testTextView.setText("Waiting");
        
		newReleaseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//System.out.println("Button Clicked");
				
				if (NetworkConnection.connectionStatus(myContext)) {
					//System.out.println("Network Works!!");
					Log.i(TAG, "Network Works!!");
					
					//myDataManager.writeStringToFile(myContext, myFileName, DataService.responseString);
					JSONData.displayDataFromFile();
					
				} else {
					//Show no connection alert
					noConnectionAlert();
					
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
					//Write the file to the system
					myDataManager.writeStringToFile(myContext, myFileName, DataService.responseString);
				
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
    
    //Custom methods to show no connection or no file alerts
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
    }
    
}
