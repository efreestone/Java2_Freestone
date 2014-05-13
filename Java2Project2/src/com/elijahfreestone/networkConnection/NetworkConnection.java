/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project2
 * 
 * Package	com.elijahfreestone.networkConnection
 * 
 * date		May 12, 2014
 */

package com.elijahfreestone.networkConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class JSONData handles network connection testing and parsing and object
 * creation of JSON Data from the API call that takes place in DataService.
 */
public class NetworkConnection {
	
	/** The response string. */
	static String responseString;

	/**
	 * Connection status.
	 * 
	 * @param myContext
	 *            the my context
	 * @return the boolean
	 */
	public static Boolean connectionStatus(Context myContext) {
		Boolean connectionBool = false;

		ConnectivityManager connectionManager = (ConnectivityManager) myContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

		if (networkInfo != null) {
			if (networkInfo.isConnected()) {
				connectionBool = true;
			}
		}
		return connectionBool;
	} // connectionStatus Close

}
