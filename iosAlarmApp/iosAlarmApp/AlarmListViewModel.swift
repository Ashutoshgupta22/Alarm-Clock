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
        
        Task {
            debugPrint("fetching alarms")
            let fetchedAlarms = await repo.getAllAlarm()
            
            await MainActor.run {
                alarmList = fetchedAlarms
            }
        }
        
    }
    
    func addAlarm(time: Date) {
        
        let alarm = AlarmData(id: 0,
                             hour: Int32(Calendar.current.component(.hour, from: time)),
                             minute: Int32(Calendar.current.component(.minute, from: time)),
                             isOn: true
                            )
        
        Task{
//            await MainActor.run {
//               // alarmList.append(alarm)
//                
//            }
            
            await repo.insertAlarm(alarm: alarm)
            fetchAlarms()
        }
    }
    
    func updateAlarm(alarm: AlarmData) {
        Task{
            await repo.updateAlarmState(alarm: alarm)
        }
    }
}
