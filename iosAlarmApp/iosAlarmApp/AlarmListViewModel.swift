//
//  AlarmListViewModel.swift
//  iosAlarmApp
//
//  Created by Ashutosh Gupta on 17/05/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class AlarmListViewModel: ObservableObject {
    
    @Published var alarmList: [Alarm] = []
    
    init() {
        fetchAlarms()
    }
    
    func fetchAlarms() {
        
    }
}
