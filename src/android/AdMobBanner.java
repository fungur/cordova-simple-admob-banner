package com.subclosure.admob;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;


public class AdMobBanner extends CordovaPlugin {
  
  private AdView av;
  
  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
  }
  
  
  @Override
  public boolean execute(String action_orig, JSONArray args_orig , final CallbackContext callbackContext) throws JSONException {
    
    final String action = action_orig ;
    final JSONArray args = args_orig ;
    
    
    
    cordova.getActivity().runOnUiThread(new Runnable() {
      public void run() {
        
        ViewGroup parentView = (ViewGroup) webView.getParent();
        
        if (av != null) {
          try {
            parentView.removeView(av);
          } catch (Exception e) {
            Log.d(this.getClass().getName(), e.getMessage());
            
          }
        }
        
        if (action.equals("showBanner")) {
          
          String publisherid ;
          try {
            publisherid = args.getString(0);
          } catch (JSONException e) {
            callbackContext.error("Missing Banner ID");
            return;
          }
          
          Log.d(this.getClass().getName(), String.format("show banner for publisher id: %s", publisherid));
          
          
          av = new AdView(cordova.getActivity());
          av.setAdSize(AdSize.SMART_BANNER);
          av.setAdUnitId(publisherid);
          
          av.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
              super.onAdLoaded();
              callbackContext.success("admob ad loaded");
            }
          });
          
          
          AdRequest.Builder b = new AdRequest.Builder();
          
          try {
            String testdevice_id = args.getString(1);
            b.addTestDevice(testdevice_id);
          } catch (JSONException e) {
            
          }
          
          
          AdRequest adRequest = b.build();
          
          av.setLayoutParams(new LinearLayout.LayoutParams(
          LayoutParams.MATCH_PARENT,
          LayoutParams.WRAP_CONTENT));
          
          av.loadAd(adRequest);
          
          parentView.addView(av);
          
        }
        
      }
    });
    
    
    return true;
  }
  
}