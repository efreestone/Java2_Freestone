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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class DetailsActivity.
 */
public class DetailsActivity extends Activity {
	String dvdTitle, releaseDate, movieRating, criticRating, audienceRating;
	TextView titleTextView, releaseTextView, movieRatingTextView, criticRatingTextView, audienceRatingTextView;
	Float ratingSelected;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
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
				//System.out.println("More Info Button Clicked");
				//Create implicit intent. This will pass a custom URL searching for the movie
				//title on RottenTomatoes.com and open it in a browser
				
				String baseURLString = "http://www.rottentomatoes.com/m/";
				String moddedTitle = dvdTitle.replace(" ", "_");
				String urlSearchMod = baseURLString + moddedTitle;
				Intent moreInfoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlSearchMod));
				//moreInfoIntent.setType(HTTP.PLAIN_TEXT_TYPE);
				//moreInfoIntent.putExtra();
				
				startActivity(moreInfoIntent);
			} // onClick Close
		}); // onClickListener Close
		
	} // onCreate Close

}
