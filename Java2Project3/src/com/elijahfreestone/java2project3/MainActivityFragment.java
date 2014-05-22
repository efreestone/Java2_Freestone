/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project3
 * 
 * Package	com.elijahfreestone.java2project3
 * 
 * date		May 21, 2014
 */

package com.elijahfreestone.java2project3;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.elijahfreestone.networkConnection.NetworkConnection;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivityFragment.
 */
public class MainActivityFragment extends Fragment {
	Button newReleaseButton;
	static ListView myListView;
	
	static String TAG = "Main Fragment";

	/* (non-Javadoc)
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	} // onAttach Close

	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// Create/inflate view and return
		View mainView = inflater.inflate(R.layout.activity_main, container);
		
		myListView = (ListView) mainView.findViewById(R.id.listView);
		View listHeader = inflater.inflate(R.layout.listview_header, null);
		myListView.addHeaderView(listHeader);
		
		// Grab button by id and set onClick with network connection check
		newReleaseButton = (Button) mainView.findViewById(R.id.newReleaseButton);
		newReleaseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (NetworkConnection.connectionStatus(MainActivity.myContext)) { 
					// System.out.println("Network Works!!");
					Log.i(TAG, "Network Works!!");

					JSONData.displayDataFromFile();
				} else {
					// Show no connection alert
					MainActivity.noConnectionAlert();
					
					Log.i(TAG, "No Network!!");
				}
			} // onClick Close
		}); // onClickListener Close
		
		return mainView;
	} // onCreateView Close

}
