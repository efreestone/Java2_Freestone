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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.elijahfreestone.networkConnection.NetworkConnection;

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
					alertDialog.setTitle(R.string.noConnectionTitle);
					// Set alert message. setMessage only has a charSequence
					// version so getString must be used.
					alertDialog.setMessage(myContext.getString(R.string.noConnectionAlert));
					alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (DialogInterface.OnClickListener) null);
					alertDialog.show();
				}
			} //onClick Close
		}); //onClickListener Close
		
		
    } //onCreate Close


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
