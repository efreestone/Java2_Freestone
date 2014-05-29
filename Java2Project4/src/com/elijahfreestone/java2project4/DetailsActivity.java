/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project4
 * 
 * Package	com.elijahfreestone.java2project4
 * 
 * date		May 23, 2014
 */

package com.elijahfreestone.java2project4;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class DetailsActivity.
 */
public class DetailsActivity extends Activity implements
		DetailsFragment.OnGetMoreInfoClicked {
	static String dvdTitle;
	String releaseDate, movieRating, criticRating, audienceRating;
	static Float ratingSelected;
	
	HashMap<String, String> saveSelectedMovie;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_fragment);

		DetailsFragment detailsFragment = (DetailsFragment) getFragmentManager()
				.findFragmentById(R.id.detailsFragment);

		// Grab intent extras to be displayed in textviews
		Intent newDetailsIntent = getIntent();
		dvdTitle = newDetailsIntent.getStringExtra("dvdTitle");
		releaseDate = newDetailsIntent.getStringExtra("releaseDate");
		movieRating = newDetailsIntent.getStringExtra("movieRating");
		criticRating = newDetailsIntent.getStringExtra("criticRating");
		audienceRating = newDetailsIntent.getStringExtra("audienceRating");

		if (newDetailsIntent != null) {
			if (detailsFragment != null) {
				detailsFragment.displayMovieDetails(dvdTitle, releaseDate,
						movieRating, criticRating, audienceRating);
			} else {
				Log.i("Details", "Details Frag is null!!");
			}
		}

		// Finish activity if device is changed to Landscape. Land xml replaces this in 2 pane
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			// detailsFragment.displayMovieDetails(dvdTitle, releaseDate,
			// movieRating, criticRating, audienceRating);
			Log.i("Details Fragment", "Details Fragment Finish called");
		}
	} // onCreate Close

	// finish is called when the activity is exited (such as the back button)
	// This creates a new intent and passes the title and rating back
	@Override
	public void finish() {
		Log.i("Details Activity", "Finish called");
		Intent detailsBackIntent = new Intent();
		detailsBackIntent.putExtra("dvdTitle", dvdTitle);
		detailsBackIntent.putExtra("releaseDate", releaseDate);
		detailsBackIntent.putExtra("movieRating", movieRating);
		detailsBackIntent.putExtra("criticRating", criticRating);
		detailsBackIntent.putExtra("audienceRating", audienceRating);
		detailsBackIntent.putExtra("ratingSelected", ratingSelected);
		setResult(RESULT_OK, detailsBackIntent);
		super.finish();
	} // finish Close
 
	
	@Override
	public void onGetMoreInfoClicked() {
		/*
		 * Create implicit intent. This will pass a custom URL searching for the
		 * movie title on RottenTomatoes.com and open it in a browser
		 */
		String baseURLString = "http://www.rottentomatoes.com/m/";
		String moddedTitle = dvdTitle.replace(" ", "_");
		String urlSearchMod = baseURLString + moddedTitle;
		Intent moreInfoIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse(urlSearchMod));

		startActivity(moreInfoIntent);
	} // onGetMoreInfoClicked Close
	
	// onSaveInstanceState grabs my array list and saves it to the bundle
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		// Grab entire array list from JSON Data
		saveSelectedMovie = MainFragment.selectedMovie;
		if (saveSelectedMovie != null && !saveSelectedMovie.isEmpty()) {
			savedInstanceState.putSerializable("stored movie",
					saveSelectedMovie);
			// Log.i("Save", "" + currentMovieList);
			Log.i("Details Save", "Movie Object Instance State Saved");
		}
	} // onSaveInstanceState Close
	
	// onRestore grabs my array list from the bundle if it exists and redisplays it
	@SuppressWarnings("unchecked")
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Grab Movie object from the bundle
		saveSelectedMovie = (HashMap<String, String>) savedInstanceState
				.getSerializable("stored");
		Log.i("File", "Size = " + saveSelectedMovie.size());

		if (saveSelectedMovie != null && !saveSelectedMovie.isEmpty()) {
			Log.i("Details Save", "Movie Details Being Restored");

			DetailsFragment detailsFragment = (DetailsFragment) getFragmentManager()
					.findFragmentById(R.id.detailsFragment);

			// Pull all extras from Intent
			String dvdTitle = saveSelectedMovie.get("dvdTitle");
			String releaseDate = saveSelectedMovie.get("releaseDate");
			String movieRating = saveSelectedMovie.get("movieRating");
			String criticRating = saveSelectedMovie.get("criticRating");
			String audienceRating = saveSelectedMovie.get("audienceRating");

			// Float ratingSelected =
			// detailsBackIntent.getExtras().getFloat("ratingSelected");
			// ratingSelectedAlert(dvdTitle, ratingSelected);

			// If the device is in landscape, display movie info in details pane
			// This is only triggered when in Details in Portrait then changing
			// device to landscape
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				detailsFragment.displayMovieDetails(dvdTitle, releaseDate,
						movieRating, criticRating, audienceRating);
				Log.i("Details", "Details Landscape");
			}

		}
	} // onRestoreInstanceState Close

}
