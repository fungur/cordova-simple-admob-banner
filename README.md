cordova-simple-admob-banner
===========================

Plugin is only for Android.
Creates a Smart Admob Banner and adds it to the bottom of app.
Adview is placed beneath Webview and not on top of content.

Plugin needs platforms/android/libs/google-play-services.jar to be present. 


use:

    admob.init({
      banner_id: "Your Banner ID",          //required
      testdevice_id: "Your Test Device ID"  //optional
    }).showBanner();

