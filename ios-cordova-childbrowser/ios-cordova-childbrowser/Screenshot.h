//
//  Screenshot.h
//  cordova-screenshot
//
//  Created by Dhaval Nagar on 22/06/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <QuartzCore/QuartzCore.h>

#ifdef CORDOVA_FRAMEWORK
#import <Cordova/CDVPlugin.h>
#else
#import "CDVPlugin.h"
#endif

@interface Screenshot : CDVPlugin {
}

- (void)saveScreenshot:(NSArray*)arguments withDict:(NSDictionary*)options;
- (void)setAllowed:(NSArray*)arguments withDict:(NSDictionary*)options;
@end