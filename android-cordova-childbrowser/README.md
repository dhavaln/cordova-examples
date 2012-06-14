android-cordova-childbrowser
================

Customized the Childbrowser behavior to add showAddressBar option to turn address bar on/off as below.

Open childbrowser with address bar

<pre>
window.plugins.childBrowser.showWebPage("http://www.google.com", { showLocationBar: true, showAddressBar: true });
</pre>

Open childbrowser without address bar

<pre>
window.plugins.childBrowser.showWebPage("http://www.google.com", { showLocationBar: true, showAddressBar: false });
</pre>