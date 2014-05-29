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

public class AlertDialogFragment extends DialogFragment {
	public static DialogType type;
	
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
		
		return super.onCreateDialog(savedInstanceState);
	} // onCreateDialog Close

}
