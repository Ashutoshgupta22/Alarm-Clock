//
//  Repository.swift
//  iosAlarmApp
//
//  Created by Ashutosh Gupta on 25/05/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

class Repository {
    
    private let alarmRepo = AlarmRepository()
    private let alarmScheduler = IOSAlarmScheduler()
    
    func getAllAlarm() async -> [AlarmData] {
       return alarmRepo.getAllAlarm()
    }
    
    func insertAlarm(alarm: AlarmData) async {
        alarmRepo.insertAlarm(alarm: alarm)
        await scheduleIosAlarm(alarm: alarm)
    }
    
    func updateAlarmState(alarm: AlarmData) async {
        alarmRepo.updateAlarmState(id: alarm.id, isOn: alarm.isOn)
    }
    
    private func scheduleIosAlarm(alarm: AlarmData) async {
        alarmScheduler.scheduleAlarm(alarm: alarm)
    }
}
