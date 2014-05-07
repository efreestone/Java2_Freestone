/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project1
 * 
 * Package	com.elijahfreestone.networkConnection
 * 
 * date		May 6, 2014
 */

package com.elijahfreestone.networkConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * The Class NetworkConnection tests is used to check for a network connection using ConnectivityManager and NetworkInfo.
 */
public class NetworkConnection {
	
	/**
	 * Connection status.
	 *
	 * @param myContext the my context
	 * @return the boolean
	 */
	public static Boolean connectionStatus(Context myContext) {
		Boolean connectionBool = false;

		ConnectivityManager connectionManager = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
		
		if (networkInfo != null) {
			if (networkInfo.isConnected()) {
				connectionBool = true;
			}
		}
		return connectionBool;
	}
}
