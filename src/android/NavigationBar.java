// Copyright (c) 2014 cranberrygame
// Email: cranberrygame@yahoo.com
// Phonegap plugin: http://www.github.com/cranberrygame
// Construct2 phonegap plugin: https://www.scirra.com/forum/viewtopic.php?f=153&t=109586
//                             https://dl.dropboxusercontent.com/u/186681453/index.html
//                             https://www.scirra.com/users/cranberrygame
// Facebook: https://www.facebook.com/profile.php?id=100006204729846
// License: MIT (http://opensource.org/licenses/MIT)
package com.cranberrygame.phonegap.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.util.Log;
//
import android.view.View;
import android.os.Handler;

public class NavigationBar extends CordovaPlugin {
	private static final String LOG_TAG = "NavigationBar";

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
	
    }
	
	@Override
	public boolean execute(String action, JSONArray args,CallbackContext callbackContext) throws JSONException {
		PluginResult result = null;
		
		//args.length()
		//args.getString(0)
		//args.getString(1)
		//args.getInt(0)
		//args.getInt(1)
		//args.getBoolean(0)
		//args.getBoolean(1)
		//JSONObject json = args.optJSONObject(0);
		//json.optString("adUnit")
		//json.optString("adUnitFullScreen")
		//JSONObject inJson = json.optJSONObject("inJson");
			
		if (action.equals("setUp")) {
			//Activity activity=cordova.getActivity();
			//webView
			//
			final boolean autoHide = args.getBoolean(0);
			
			final CallbackContext delayedCB = callbackContext;
			cordova.getActivity().runOnUiThread(new Runnable(){
				@Override
				public void run() {						
					_setUp(autoHide);
					
					delayedCB.sendPluginResult(new PluginResult(PluginResult.Status.OK));
					//delayedCB.success();
					//delayedCB.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
					//delayedCB.error();
				}
			});	
			
			return true;
		}
		else if (action.equals("hide")) {
			//Activity activity=cordova.getActivity();
			//webView
			//
			
			final CallbackContext delayedCB = callbackContext;
			cordova.getActivity().runOnUiThread(new Runnable(){
				@Override
				public void run() {						
					_hide();
					
					delayedCB.sendPluginResult(new PluginResult(PluginResult.Status.OK));
					//delayedCB.success();
					//delayedCB.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
					//delayedCB.error();
				}
			});	
			
			return true;
		}
		
		return false; // Returning false results in a "MethodNotFound" error.
	}
	//-------------------------------------

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) 
	private void _setUp(boolean autoHide){
		if (autoHide) {
			//http://www.youtube.com/watch?v=O53rxBjZbJ8
			//http://www.youtube.com/watch?v=Xw9TIS_JsPM		
			//http://bin-liu.blogspot.kr/2012/03/how-to-hide-and-display-navigation-bar.html
			Activity activity=cordova.getActivity();
			activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);				
		
			final CordovaInterface cordova_final = cordova;
			//http://stackoverflow.com/questions/11762306/listen-for-first-touchevent-when-using-system-ui-flag-hide-navigation
			//http://stackoverflow.com/questions/15103339/android-full-screen-modeics-first-touch-shows-the-navigation-bar
			//http://developer.android.com/reference/android/view/View.OnSystemUiVisibilityChangeListener.html	
			webView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener(){
				@Override
				public void onSystemUiVisibilityChange(int vis) {
					if(vis == 0){
						
						//http://stackoverflow.com/questions/3072173/how-to-call-a-method-after-a-delay-in-android
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Activity activity=cordova_final.getActivity();
								activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);				
							}
						}, 3000);//after ms		    		
					}
				}
			});
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) 
	private void _hide(){
		Activity activity=cordova.getActivity();
		activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);				
	}
}