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
import android.widget.RatingBar;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class DetailsActivity.
 */
public class DetailsActivity extends Activity implements DetailsActivityFragment.OnGetMoreInfoClicked {
	static String dvdTitle;
	String releaseDate;
	String movieRating;
	String criticRating;
	String audienceRating;
	TextView titleTextView, releaseTextView, movieRatingTextView, criticRatingTextView, audienceRatingTextView;
	static Float ratingSelected;
	RatingBar movieRatingBar;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_fragment);

		ratingSelected = 0.0f;

//		// Grab all text views by id
//		titleTextView = (TextView) findViewById(R.id.titleTextView);
//		releaseTextView = (TextView) findViewById(R.id.releaseTextView);
//		movieRatingTextView = (TextView) findViewById(R.id.movieRatingTextView);
//		criticRatingTextView = (TextView) findViewById(R.id.criticRatingTextView);
//		audienceRatingTextView = (TextView) findViewById(R.id.audienceRatingTextView);
		
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
		

//		// Set textviews with strings from intent extras
//		titleTextView.setText("DVD Title: " + dvdTitle);
//		releaseTextView.setText("Released: " + releaseDate);
//		movieRatingTextView.setText("MPAA Rating: " + movieRating);
//		criticRatingTextView.setText("Critic Rating: " + criticRating);
//		audienceRatingTextView.setText("Audience Rating: " + audienceRating);

//		// Grab button by id and set onClick for implicit intent
//		Button moreInfoButton = (Button) findViewById(R.id.moreInfoButton);
//		moreInfoButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// Create implicit intent. This will pass a custom URL searching for the movie
//				// title on RottenTomatoes.com and open it in a browser
//
//				String baseURLString = "http://www.rottentomatoes.com/m/";
//				String moddedTitle = dvdTitle.replace(" ", "_");
//				String urlSearchMod = baseURLString + moddedTitle;
//				Intent moreInfoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSearchMod));
//
//				startActivity(moreInfoIntent);
//			} // onClick Close
//		}); // onClickListener Close

//		movieRatingBar = (RatingBar) findViewById(R.id.movieRatingBar);
//		movieRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
//
//			@Override
//			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//						ratingSelected = movieRatingBar.getRating();
//			}
//		});

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
