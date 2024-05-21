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
    
    @Published var alarmList: [AlarmData] = []
    let repo = Repository()
    
    init() {
//        fetchAlarms()
    }
    
    func formatTime(time: AlarmData) -> String {
        return Utility().formatTime(hour24: time.hour, min: time.minute)
    }
    
    func amOrPm(time: AlarmData) -> String {
        return Utility().amOrPm(time: time).uppercased()
    }
    
    func fetchAlarms() {
        debugPrint("fetching alarms")
        alarmList = repo.getAll()
        
    }
    
    func addAlarm(time: Date) {
        
        let alarm = AlarmData(id: Int32(alarmList.count),
                             hour: Int32(Calendar.current.component(.hour, from: time)),
                             minute: Int32(Calendar.current.component(.minute, from: time)),
                             isSet: true
                            )
        
        alarmList.append(alarm)
        repo.insert(alarmData: alarm)
    }
}
