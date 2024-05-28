//
//  AppDelegate.swift
//  iosAlarmApp
//
//  Created by Ashutosh Gupta on 28/05/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

class AppDelegate: UIResponder, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .sound, .badge]) { granted, error in
            if granted {
                print("Notification authorization granted")
            }else if let error = error {
                print("Error requesting notification permissions: \(error.localizedDescription)")
            } else {
                print("Notification authorization denied")
            }
        }
        return true
    }
}
