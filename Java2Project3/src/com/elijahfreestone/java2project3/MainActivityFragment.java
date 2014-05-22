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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivityFragment.
 */
public class MainActivityFragment extends Fragment {
	Button newReleaseButton;
	static ListView myListView;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Create/inflate view and return
		View mainView = inflater.inflate(R.layout.activity_main, container);
		
		myListView = (ListView) mainView.findViewById(R.id.listView);
		View listHeader = inflater.inflate(R.layout.listview_header, null);
		myListView.addHeaderView(listHeader);
		
		return mainView;
	} // onCreateView Close

}
