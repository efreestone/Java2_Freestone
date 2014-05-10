/*
 * @author	Elijah Freestone 
 *
 * Project	Java2Project1
 * 
 * Package	com.elijahfreestone.java2project1
 * 
 * date		May 9, 2014
 */

package com.elijahfreestone.java2project1;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class DataService extends IntentService {
	static String TAG = "DataService";
	
	public static final String MESSENGER_KEY = "messenger";
	public static final String TIME_KEY = "time";

	public DataService() {
		super("DataService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		TAG = "onHandleIntent";
		
		Log.i(TAG, "started");
		
		Bundle extras = intent.getExtras();
		Messenger messenger = (Messenger) extras.get(MESSENGER_KEY);
		
		String timer = extras.getString(TIME_KEY);
		
		int countdown = 0;
		
		try {
			countdown = Integer.parseInt(timer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage().toString());
			
		}
		
		while (countdown > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Log.e("Sleep", e.getMessage().toString());
				
			}
			
			countdown--;
			
			Log.i(TAG, "counter = " + String.valueOf(countdown));
		}
		
		Log.i("onHandleIntent", "counter is done");
			
		Message message = Message.obtain();
		message.arg1 = Activity.RESULT_OK;
		message.obj = "Service is done";
		
		try {
			messenger.send(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage().toString());
		}	
	}

}
