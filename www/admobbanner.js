window.admob = {

  testdevice_id: "",

  showBanner: function showBanner() {
    cordova.exec(function(success) {
      console.log("admob called " + success);
    }, function(err) {
      console.log('Admob Error ' + err);
    }, "AdMobBanner", "showBanner", [this.banner_id, this.testdevice_id]);
  },

  init: function(options) {

    this.banner_id = options.banner_id;
    this.testdevice_id = options.testdevice_id ? options.testdevice_id :
      "";

    var admob = this;

    // Listen for orientation changes and reload ad ( for smart banners to resize properly )
    window.addEventListener("orientationchange", function() {
      admob.showBanner();
    }, false);

    return this;
  }

};