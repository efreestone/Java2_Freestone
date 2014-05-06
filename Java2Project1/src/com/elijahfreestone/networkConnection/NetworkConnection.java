package com.elijahfreestone.networkConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnection {
	
	// Method to test network
	public static Boolean connectionStatus(Context myContext) {
		// Set connection bool to false
		Boolean connectionBool = false;

		// Create connectivity manager
		ConnectivityManager connectionManager = (ConnectivityManager) myContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// Create Network info
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
		// Check if network info is null
		if (networkInfo != null) {
			// Check if network info is connected
			if (networkInfo.isConnected()) {
				// Set connection bool to true
				connectionBool = true;
			}
		}
		// Return connection bool
		return connectionBool;
	}

}
