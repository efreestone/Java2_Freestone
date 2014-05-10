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
import android.widget.TextView;
import com.elijahfreestone.networkConnection.JSONData;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {
	static Context myContext;
	static String TAG = "MainActivity";
	static String responseString = null;
	final MyServiceHandler myServiceHandler = new MyServiceHandler(this);
	String testString = "10";
	
	

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //final MyServiceHandler myServiceHandler = new MyServiceHandler(this);
        
        myContext = this;
        
        //String testString = "10";
        
        Button newReleaseButton = (Button) findViewById(R.id.newReleaseButton);
        final TextView testTextView = (TextView) findViewById(R.id.test_textview);
        
//        //Create Handler for Service
//		Handler dataHandler = new Handler() {
//
//			@Override
//			public void handleMessage(Message msg) {
//				//super.handleMessage(msg);
//				String responseString = null;
//				
//				if (msg.arg1 == RESULT_OK && msg.obj != null) {
//					try {
//						responseString = (String) msg.obj;
//					} catch (Exception e) {
//						Log.e("handleMessage", e.getMessage().toString());
//					}
//
//					testTextView.setText(responseString);
//				}
//			}
//		}; //handleMessage Close
		
//		//Create Messenger class for ref to handler
//		Messenger dataMessenger = new Messenger(myServiceHandler);
//		
//		//Create Intent to start service
//		Intent startDataIntent = new Intent(myContext, DataService.class);
//		startDataIntent.putExtra(DataService.MESSENGER_KEY, dataMessenger);
//		startDataIntent.putExtra(DataService.TIME_KEY, testString);
//		
//		//Start the service
//		startService(startDataIntent);
        
        //Call retrieveData to start the Service and get JSON data from the API
        retrieveData();
		
		testTextView.setText("Waiting");
        
		newReleaseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//String testString = "10";
				//System.out.println("Button Clicked");
				
				if (JSONData.connectionStatus(myContext)) {
					//System.out.println("Network Works!!");
					Log.i(TAG, "Network Works!!");
					
					
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
					//displatDataFromFile
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
    	startDataIntent.putExtra(DataService.TIME_KEY, testString);
    			
    	//Start the service
    	startService(startDataIntent);
    	
    }
    
}
