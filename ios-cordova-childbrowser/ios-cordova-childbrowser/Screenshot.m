//
//  Screenshot.m
//  cordova-screenshot
//
//  Created by Dhaval Nagar on 22/06/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//
//
//  Screenshot.h
//
//  Created by Simon Madine on 29/04/2010.
//  Copyright 2010 The Angry Robot Zombie Factory.
//   - Converted to Cordova 1.6.1 by Josemando Sobral.
//  MIT licensed
//
//  Modifications to support orientation change by @ffd8
//

#import "Screenshot.h"
#import "AppDelegate.h"

@implementation Screenshot

@synthesize webView;

- (void)saveScreenshot:(NSArray*)arguments withDict:(NSDictionary*)options
{
	CGRect imageRect;
	CGRect screenRect = [[UIScreen mainScreen] bounds];
    
	// statusBarOrientation is more reliable than UIDevice.orientation
	UIInterfaceOrientation orientation = [UIApplication sharedApplication].statusBarOrientation;
    
	if (orientation == UIInterfaceOrientationLandscapeLeft || orientation == UIInterfaceOrientationLandscapeRight) { 
		// landscape check
		imageRect = CGRectMake(0, 0, CGRectGetHeight(screenRect), CGRectGetWidth(screenRect));
	} else {
		// portrait check
		imageRect = CGRectMake(0, 0, CGRectGetWidth(screenRect), CGRectGetHeight(screenRect));
	}
    
	UIGraphicsBeginImageContext(imageRect.size);
    
	CGContextRef ctx = UIGraphicsGetCurrentContext();
	[[UIColor blackColor] set];
	CGContextTranslateCTM(ctx, 0, 0);
	CGContextFillRect(ctx, imageRect);
    
	[webView.layer renderInContext:ctx];
    
	UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
	UIImageWriteToSavedPhotosAlbum(image, nil, nil, nil);
	UIGraphicsEndImageContext();
    
	UIAlertView *alert= [[UIAlertView alloc] initWithTitle:nil message:@"Image Saved" delegate:self cancelButtonTitle:@"OK" otherButtonTitles:nil];
	[alert show];
	[alert release];
}

- (void) setAllowed:(NSArray *)arguments withDict:(NSDictionary *)options
{
    NSLog(@"Allow orientation");
    
    AppDelegate* appDelegate = (AppDelegate*) [UIApplication sharedApplication].delegate;
    
    NSMutableArray *allowed = [NSMutableArray array];
    
    if ([[options objectForKey:@"pp"] intValue] == 1)
        [allowed addObject:[NSNumber numberWithInt:UIDeviceOrientationPortrait]];
    
    if ([[options valueForKey:@"pd"] intValue] == 1)
        [allowed addObject:[NSNumber numberWithInt:UIDeviceOrientationPortraitUpsideDown]];
    
    if ([[options valueForKey:@"ll"] intValue] == 1)
        [allowed addObject:[NSNumber numberWithInt:UIDeviceOrientationLandscapeLeft]];
    
    if ([[options objectForKey:@"lr"] intValue] == 1)
        [allowed addObject:[NSNumber numberWithInt:UIDeviceOrientationLandscapeRight]];
    
    [appDelegate setOrientation:allowed];
}
@end
