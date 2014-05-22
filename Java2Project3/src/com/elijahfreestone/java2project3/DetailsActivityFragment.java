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

import com.elijahfreestone.java2project3.MainActivityFragment.OnListItemSelected;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class DetailsActivityFragment extends Fragment {
	TextView titleTextView, releaseTextView, movieRatingTextView, criticRatingTextView, audienceRatingTextView;
	String dvdTitle; //, releaseDate, movieRating, criticRating, audienceRating;
	RatingBar movieRatingBar;
	Float ratingSelected;
	
	public interface OnGetMoreInfoClicked {
		void onGetMoreInfoClicked();
	}
	
	private OnGetMoreInfoClicked parentActivity;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		if (activity instanceof OnGetMoreInfoClicked) {
			parentActivity = (OnGetMoreInfoClicked) activity;
		} else {
			Log.e("Detail Fragment", "Must implement OnGetMoreInfoClick");
		}
		 
	} // onAttach Close

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Create/inflate view and return
		View detailsView = inflater.inflate(R.layout.activity_details, container);
		
		ratingSelected = 0.0f;
		
		// Grab all text views by id
		titleTextView = (TextView) detailsView.findViewById(R.id.titleTextView);
		releaseTextView = (TextView) detailsView.findViewById(R.id.releaseTextView);
		movieRatingTextView = (TextView) detailsView.findViewById(R.id.movieRatingTextView);
		criticRatingTextView = (TextView) detailsView.findViewById(R.id.criticRatingTextView);
		audienceRatingTextView = (TextView) detailsView.findViewById(R.id.audienceRatingTextView);
		
//		// Grab intent extras to be displayed in textviews
//		Intent newDetailsIntent = getIntent();
//		dvdTitle = newDetailsIntent.getStringExtra("dvdTitle");
//		releaseDate = newDetailsIntent.getStringExtra("releaseDate");
//		movieRating = newDetailsIntent.getStringExtra("movieRating");
//		criticRating = newDetailsIntent.getStringExtra("criticRating");
//		audienceRating = newDetailsIntent.getStringExtra("audienceRating");

//		// Set textviews with strings from intent extras
//		titleTextView.setText("DVD Title: " + dvdTitle);
//		releaseTextView.setText("Released: " + releaseDate);
//		movieRatingTextView.setText("MPAA Rating: " + movieRating);
//		criticRatingTextView.setText("Critic Rating: " + criticRating);
//		audienceRatingTextView.setText("Audience Rating: " + audienceRating);
		
		// Grab button by id and set onClick for implicit intent
		Button moreInfoButton = (Button) detailsView.findViewById(R.id.moreInfoButton);
		moreInfoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				parentActivity.onGetMoreInfoClicked();
//				// Create implicit intent. This will pass a custom URL searching
//				// for the movie
//				// title on RottenTomatoes.com and open it in a browser
//				
//				dvdTitle = DetailsActivity.dvdTitle;
//
//				String baseURLString = "http://www.rottentomatoes.com/m/";
//				String moddedTitle = dvdTitle.replace(" ", "_");
//				String urlSearchMod = baseURLString + moddedTitle;
//				Intent moreInfoIntent = new Intent(Intent.ACTION_VIEW, Uri
//						.parse(urlSearchMod));
//
//				startActivity(moreInfoIntent);
			} // onClick Close
		}); // onClickListener Close
		
		movieRatingBar = (RatingBar) detailsView.findViewById(R.id.movieRatingBar);
		movieRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				DetailsActivity.ratingSelected = movieRatingBar.getRating();
			}
		});
		
		return detailsView;
	} // onCreateView Close
	
	// displayMovieDetails displays details of the movie selected, called from DetailsActivity
	public void displayMovieDetails(String dvdTitle, String releaseDate,
			String movieRating, String criticRating, String audienceRating) {
		// Set textviews with strings from intent extras
		titleTextView.setText("DVD Title: " + dvdTitle);
		releaseTextView.setText("Released: " + releaseDate);
		movieRatingTextView.setText("MPAA Rating: " + movieRating);
		criticRatingTextView.setText("Critic Rating: " + criticRating);
		audienceRatingTextView.setText("Audience Rating: " + audienceRating);
	}
}
