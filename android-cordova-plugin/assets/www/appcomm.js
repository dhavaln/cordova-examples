var AppComm = function(){
	
};

AppComm.prototype.updateValues = function(json) {
	var data = window.JSON.parse(json);
	$("#pluginResult").html(data.value1 + ", " + data.value2);
};

cordova.addConstructor(function() {
    cordova.addPlugin("appcomm", new AppComm());
});