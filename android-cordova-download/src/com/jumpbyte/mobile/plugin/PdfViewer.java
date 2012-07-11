/*
 * PhoneGap is available under *either* the terms of the modified BSD license *or* the
 * MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
 *
 * Copyright (c) 2005-2010, Nitobi Software Inc.
 * Copyright (c) 2010, IBM Corporation
 */
package com.jumpbyte.mobile.plugin;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;

public class PdfViewer extends Plugin {

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action        The action to execute.
     * @param args          JSONArry of arguments for the plugin.
     * @param callbackId    The callback id used when calling back into JavaScript.
     * @return              A PluginResult object with a status and message.
     */
    public PluginResult execute(String action, JSONArray args, String callbackId) {
    	
    	   	
        PluginResult.Status status = PluginResult.Status.OK;
        String result = "";

        try {
            if (action.equals("showPdf")) {
                result = this.showPdf(args.getString(0));
                if (result.length() > 0) {
                    status = PluginResult.Status.ERROR;
                }
            }
            return new PluginResult(status, result);
        } catch (JSONException e) {
            return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
        }
    }

    /**
     * Identifies if action to be executed returns a value and should be run synchronously.
     *
     * @param action    The action to execute
     * @return          T=returns value
     */
    public boolean isSynch(String action) {
        return false;
    }

    /**
     * Called by AccelBroker when listener is to be shut down.
     * Stop listener.
     */
    public void onDestroy() {
    }

    
    
    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------

    public String showPdf(String fileName) {
    	
    	File file = new File(fileName);

    	Log.i("PdfViewer", "open file "+ fileName);
//        if (file.exists()) {
        	Log.i("PdfViewer", "file exist");
        	try {
	        	Uri path = Uri.fromFile(file);
	            Intent intent = new Intent(Intent.ACTION_VIEW);
	            intent.setDataAndType(path, "application/pdf");
	            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            
            
                //intent.setData(Uri.parse(fileName));
                this.ctx.startActivity(intent);
                return "";
            } catch (android.content.ActivityNotFoundException e) {
                System.out.println("PdfViewer: Error loading url "+fileName+":"+ e.toString());
                return e.toString();
            }            
/*
        }else{
        	Log.i("PdfViewer", "file not exist");
        	return "file not found";
        }
  */  	
    	
    	
        
    }

}
