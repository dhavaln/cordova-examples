package com.dhaval.mobile;

import org.apache.cordova.DroidGap;
import org.apache.cordova.api.LOG;
import org.json.JSONException;
import org.json.JSONObject;

import com.dhaval.mobile.plugin.AppComm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class App extends DroidGap {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.loadUrl("file:///android_asset/www/index.html");

		Thread t = new Thread() {
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						sleep(5000);
						sendValue("value " + i, "another vlaue " + i);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		};
		t.start();
	}

	public void sendValue(String value1, String value2) {
		JSONObject data = new JSONObject();
		try {
			data.put("value1", value1);
			data.put("value2", value2);
		} catch (JSONException e) {
			Log.e("CommTest", e.getMessage());
		}
		String js = String.format("window.plugins.appcomm.updateValues('%s');",
				data.toString());
		this.sendJavascript(js);
	}
}