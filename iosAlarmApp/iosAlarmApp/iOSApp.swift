import SwiftUI

@available(iOS 17.0, *)
@main
struct iOSApp: App {
    
    //connect AppDelegate
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
