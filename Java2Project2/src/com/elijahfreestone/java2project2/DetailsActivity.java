/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project2
 * 
 * Package	com.elijahfreestone.java2project2
 * 
 * date		May 15, 2014
 */

package com.elijahfreestone.java2project2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class DetailsActivity.
 */
public class DetailsActivity extends Activity {
	String dvdTitle, releaseDate, movieRating, criticRating, audienceRating;
	TextView titleTextView, releaseTextView, movieRatingTextView, criticRatingTextView, audienceRatingTextView;
	Float ratingSelected;
	RatingBar movieRatingBar;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		ratingSelected = 0.0f;
		
		// Grab all text views by id
		titleTextView = (TextView) findViewById(R.id.titleTextView);
		releaseTextView = (TextView) findViewById(R.id.releaseTextView);
		movieRatingTextView = (TextView) findViewById(R.id.movieRatingTextView);
		criticRatingTextView = (TextView) findViewById(R.id.criticRatingTextView);
		audienceRatingTextView = (TextView) findViewById(R.id.audienceRatingTextView);
		
		// Grab intent extras to be displayed in textviews
		Intent newDetailsIntent = getIntent();
		dvdTitle = newDetailsIntent.getStringExtra("dvdTitle");
		releaseDate = newDetailsIntent.getStringExtra("releaseDate");
		movieRating = newDetailsIntent.getStringExtra("movieRating");
		criticRating = newDetailsIntent.getStringExtra("criticRating");
		audienceRating = newDetailsIntent.getStringExtra("audienceRating");
		
		// Set textviews with strings from intent extras
		titleTextView.setText("DVD Title: " + dvdTitle);
		releaseTextView.setText("Released: " + releaseDate);
		movieRatingTextView.setText("MPAA Rating: " + movieRating);
		criticRatingTextView.setText("Critic Rating: " + criticRating);
		audienceRatingTextView.setText("Audience Rating: " + audienceRating);
		
		// Grab button by id and set onClick for implicit intent
		Button moreInfoButton = (Button) findViewById(R.id.moreInfoButton);
		moreInfoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Create implicit intent. This will pass a custom URL searching for the movie
				//title on RottenTomatoes.com and open it in a browser
				
				String baseURLString = "http://www.rottentomatoes.com/m/";
				String moddedTitle = dvdTitle.replace(" ", "_");
				String urlSearchMod = baseURLString + moddedTitle;
				Intent moreInfoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSearchMod));
				
				startActivity(moreInfoIntent);
			} // onClick Close
		}); // onClickListener Close
		
		movieRatingBar = (RatingBar) findViewById(R.id.movieRatingBar);
		movieRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				ratingSelected = movieRatingBar.getRating();
			}
		});		
		
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
}
