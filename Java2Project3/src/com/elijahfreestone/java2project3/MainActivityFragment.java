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

import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.elijahfreestone.networkConnection.NetworkConnection;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivityFragment.
 */
public class MainActivityFragment extends Fragment {
	Button newReleaseButton;
	static ListView myListView;
	Context myContext;
	static String TAG = "Main Fragment";
	
	/**
	 * The Interface onListItemSelected.
	 */
	public interface onListItemSelected {
		
		/**
		 * Start activity for result.
		 *
		 * @param detailsIntent the details intent
		 * @param requestCode the request code
		 */
		void startActivityForResult(Intent detailsIntent, int requestCode);
	}
	
	private onListItemSelected parentActivity;

	/* (non-Javadoc)
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		if (activity instanceof onListItemSelected) {
			parentActivity = (onListItemSelected) activity;
		} else {
			Log.e(TAG, "Must implement onListItemSelected");
		}
		 
	} // onAttach Close

	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		myContext = MainActivity.myContext;
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
		
		// Create onItemClickListener for the listview. This grabs details of the object selected,
		// creates new intent, and passes object details to Deatils Activty for display
		myListView.setOnItemClickListener(new OnItemClickListener() {
			HashMap<String, String> selectedMovie;
			String dvdTitle, releaseDate, movieRating, criticRating, audienceRating;

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Grab object selected from ArrayList as a HashMap and split
				// into details strings.
				selectedMovie = JSONData.myList.get(position - 1);
				dvdTitle = selectedMovie.get("dvdTitle");
				releaseDate = selectedMovie.get("releaseDate");
				movieRating = selectedMovie.get("movieRating");
				criticRating = selectedMovie.get("criticRating");
				audienceRating = selectedMovie.get("audienceRating");
				Log.i("File Selected", dvdTitle);

				// Create explicit intent and pass dvd details as extras
				Intent detailsIntent = new Intent(MainActivity.myContext, DetailsActivity.class);
				detailsIntent.putExtra("dvdTitle", dvdTitle);
				detailsIntent.putExtra("releaseDate", releaseDate);
				detailsIntent.putExtra("movieRating", movieRating);
				detailsIntent.putExtra("criticRating", criticRating);
				detailsIntent.putExtra("audienceRating", audienceRating);

				parentActivity.startActivityForResult(detailsIntent, 0);
			} // onItemClick Close
		}); // setOnItemClickListener Close
		
		return mainView;
	} // onCreateView Close

}
