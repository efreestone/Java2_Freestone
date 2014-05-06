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



import com.elijahfreestone.cities.NetworkConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//import com.elijahfreestone.networkConnection.NetworkConnection;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {
	static Context myContext;
	static String TAG = "MAIN ACTIVITY";

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myContext = this;
        
        System.out.println("onCreate Working");
        
        Button newReleaseButton = (Button) findViewById(R.id.newReleaseButton);
        
		newReleaseButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("Button Clicked");
				//Toast.makeText(myContext, "Button Clicked", Toast.LENGTH_LONG).show();
				
				if (NetworkConnection.connectionStatus(myContext)) {
					System.out.println("Network Works!!");
					
				} else {
					// Create alert dialog for no connection
					AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
					// Set dialog title to Connection Error
					alertDialog.setTitle(R.string.noConnectionTitle);
					// Set alert message. setMessage only has a charSequence
					// version so egtString must be used.
					alertDialog.setMessage(myContext.getString(R.string.noConnectionAlert));
					// Set OK Button
					alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
					// Show Alert Dailog
					alertDialog.show();
				}
			}


//			@Override
//			public void onClick(View v) {
//
//				// Check network connection
//				if (NetworkConnection.connectionStatus(myContext)) {
//					// displayTempView.setText("Yay it works!!");
//					// Create instance of get data class which is located in
//					// NetworkConnection.jar
//					getData data = new getData();
//					// Call get data and pass the full URL for the api call
//					data.execute(fullURLString);
//
//					// Check viewInflated bool
//					if (viewInflated == false) {
//						// Set bool to true
//						viewInflated = true;
//						inflateView.addView(myView);
//						// Change style of displayed temp/forecast
//						displayTempView.setTextAppearance(myContext,
//								R.style.displayStyle);
//					}
//				} else {
//					// viewInflated = false;
//					// Create alert dialog for no connection
//					AlertDialog alertDialog = new AlertDialog.Builder(
//							MainActivity.this).create();
//					// Set dialog title to Connection Error
//					alertDialog.setTitle(R.string.noConnectionTitle);
//					// Set alert message. setMessage only has a charSequence
//					// version so egtString must be used.
//					alertDialog.setMessage(myContext
//							.getString(R.string.noConnectionAlert));
//					// Set OK Button
//					alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
//							"OK", (DialogInterface.OnClickListener) null);
//					// Show Alert Dailog
//					alertDialog.show();
//				}
//			}
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				
//			}
		});
    }


    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}
