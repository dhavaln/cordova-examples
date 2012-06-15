ios-cordova-childbrowser
========================

This is a base project which uses Cordova 1.7 along with Childbrowser.

Process to run childbrowser plugin in Cordova:

* Create new project with Cordova based application template
* Run the application first time and add the generated /www folder in project from Files --> Add file to <your project>
* Run it again and make sure it triggers the deviceready event and shows the alert that Cordova is running
* Now copy all the childbrowser files in the project folder and add them from Files --> Add file to <your project> menu
* Add the ChildBrowser.js file in the /www folder
* Go to Supporting Files folder of your project and open Cordova.plist
* Add new entry in Plugins for ChildBrowserCommand (Key = ChildBrowserCommand, Value = ChildBrowserCommand)
* Add new entry in ExternalHosts for * (Key = Item 0, Value = 0). * will allow any host to be reachable from the application which you can customize to your need once the plugin starts running.
* Add the ChildBrowser.js file in your index.html and add following code to test the plugin

<pre>
&lt;script type="text/javascript"&gt;
function openGoogle(){
	var root = this;
	cb = window.plugins.childBrowser;
	
	if(cb != null) {
	   cb.showWebPage("http://google.com");
	}else
       alert("childbrowser is null");
	}
}
&lt;/script&gt;
&lt;button&gt; onclick="openGoogle">Open Google&lt;/button&gt;
</pre>
	
This process will create a basic application in which browser is running. Once it is running properly you can customize it your requirements.

Update
------

Following change in the ChildBrowser.js triggers events properly.

<pre>
if (cordovaRef && cordovaRef.addConstructor) {
	 cordovaRef.addConstructor(ChildBrowser.install);
	
	 // Make the ChildBrowser global
	 window.ChildBrowser = ChildBrowser;
} else {
	 console.log("ChildBrowser Cordova Plugin could not be installed.");
	 return null;
}
</pre>