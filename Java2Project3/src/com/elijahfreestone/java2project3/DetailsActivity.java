/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project3
 * 
 * Package	com.elijahfreestone.java2project3
 * 
 * date		May 19, 2014
 */

package com.elijahfreestone.java2project3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class DetailsActivity.
 */
public class DetailsActivity extends Activity implements DetailsActivityFragment.OnGetMoreInfoClicked {
	static String dvdTitle;
	String releaseDate, movieRating, criticRating, audienceRating;
	static Float ratingSelected;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_fragment);
		
		// Grab intent extras to be displayed in textviews
		Intent newDetailsIntent = getIntent();
		dvdTitle = newDetailsIntent.getStringExtra("dvdTitle");
		releaseDate = newDetailsIntent.getStringExtra("releaseDate");
		movieRating = newDetailsIntent.getStringExtra("movieRating");
		criticRating = newDetailsIntent.getStringExtra("criticRating");
		audienceRating = newDetailsIntent.getStringExtra("audienceRating");
		
		if (newDetailsIntent != null) {
			DetailsActivityFragment detailsFragment = (DetailsActivityFragment) getFragmentManager().findFragmentById(R.id.detailsFragment);
			
			if (detailsFragment != null) {
				detailsFragment.displayMovieDetails(dvdTitle, releaseDate, movieRating, criticRating, audienceRating);
			} else {
				Log.i("Details", "Details Frag is null!!");
			}
		}

	} // onCreate Close

	// finish is called when the activity is exited (such as the back button)
	// This creates a new intent and passes the title and rating back
	@Override
	public void finish() {
		Log.i("Details Activity", "Finish called");
		Intent detailsBackIntent = new Intent();
		detailsBackIntent.putExtra("dvdTitle", dvdTitle);
		detailsBackIntent.putExtra("ratingSelected", ratingSelected);
		setResult(RESULT_OK, detailsBackIntent);
		super.finish();
	}

	@Override
	public void onGetMoreInfoClicked() {
		// Create implicit intent. This will pass a custom URL searching for the movie
		// title on RottenTomatoes.com and open it in a browser

		String baseURLString = "http://www.rottentomatoes.com/m/";
		String moddedTitle = dvdTitle.replace(" ", "_");
		String urlSearchMod = baseURLString + moddedTitle;
		Intent moreInfoIntent = new Intent(Intent.ACTION_VIEW, Uri
				.parse(urlSearchMod));

		startActivity(moreInfoIntent);
		
	}
}
