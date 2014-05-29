/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project4
 * 
 * Package	com.elijahfreestone.java2project4
 * 
 * date		May 29, 2014
 */

package com.elijahfreestone.java2project4;

import com.elijahfreestone.java2project4.MainActivity.DialogType;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;

// TODO: Auto-generated Javadoc
/**
 * The Class AlertDialogFragment handles alert dialog fragments for the action bar.
 */
public class AlertDialogFragment extends DialogFragment {
	public static DialogType type;
	
	/* (non-Javadoc)
	 * @see android.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		
		// Switch statement for handling Action Bar item clicks accordingly
		switch (type) {
		case SEARCH:
			
			break;
			
		case FAVORITES:
			
			break;
			
		case PREFERENCES:
			
			break;

		default:
			break;
		}
		
		return alertBuilder.create();
	} // onCreateDialog Close
	
	/*
	 * newInstance returns a new instance of AlertDialogFragment
	 */
	public static AlertDialogFragment newInstance(DialogType dialogType) {
		type = dialogType;
		return new AlertDialogFragment();
	}

}
