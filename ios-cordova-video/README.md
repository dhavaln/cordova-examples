ios-cordova-video
========================

Simple example where a Vimeo video is embedded as an iframe in the html page.

The application opens the Vimeo video inside the app but opens others links in Safari browser.

I have changed the following function in MainViewController.m file to do the direction.

<pre>
	- (BOOL) webView:(UIWebView*)theWebView shouldStartLoadWithRequest:(NSURLRequest*)request navigationType:(UIWebViewNavigationType)navigationType
	{
	    NSURL *url = [request URL];
	    NSString *host = [request.URL host];

	    if(host != NULL || host != nil){
	        if ([host rangeOfString:@"vimeo.co"].location != NSNotFound) {
	            // NSLog(@"string contains google.com");
	            //[[UIApplication sharedApplication] openURL:[request URL]];
	            return YES;
	        }else{
	            if ([[url scheme] isEqualToString:@"http"] || [[url scheme] isEqualToString:@"https"]) {
	                [[UIApplication sharedApplication] openURL:url];
	                return NO;
	            }
	            else {
	                return [ super webView:theWebView shouldStartLoadWithRequest:request navigationType:navigationType ];
	            }
	        }
	    }

	    BOOL result = [super webView:theWebView shouldStartLoadWithRequest:request navigationType:navigationType];
	}
</pre>