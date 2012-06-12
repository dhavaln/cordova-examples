package com.dhaval.mobile.plugin;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class AppComm extends Plugin{

	private static AppComm instance;
	
	public AppComm() {
		instance = this;
	}
	
	public static AppComm getInstance() {
		return instance;
	}
	
	@Override
	public PluginResult execute(String arg0, JSONArray arg1, String arg2) {
		return null;
	}
	
	public void sendValue(String value1, String value2) {
		JSONObject data = new JSONObject();
		try {
			data.put("value1", value1);
			data.put("value2", value2);
		} catch (JSONException e) {
			Log.e("CommTest", e.getMessage());
		}
		String js = String.format(
				"window.plugins.commtest.updateValues('%s');",
				data.toString());
		this.sendJavascript(js);
	}
}
