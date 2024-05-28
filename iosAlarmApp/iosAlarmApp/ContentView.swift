import SwiftUI
import shared

@available(iOS 17.0, *)
struct ContentView: View {

    @ObservedObject private var viewModel = AlarmListViewModel()
    @State private var showTimePicker = false

    @available(iOS 17.0, *)
    var body: some View {
      
        NavigationStack() {
            List{
                ForEach($viewModel.alarmList, id: \.id) { $alarm in

                    HStack{
                            Text( viewModel.formatTime(time: alarm) ).font(.custom("AlarmFontSize", size: 60))
                        
                        +
                            Text( viewModel.amOrPm(time: alarm)
                                ).font(.custom("AlarmFontSize", size: 30))
  
                        Spacer()
                        
                        Toggle(isOn: $alarm.isOn) {}
                            .onChange(of: alarm.isOn) {
                                viewModel.updateAlarm(alarm: alarm)
                            }
                            .labelsHidden()
                    }
                
                
                }
            }
            .onAppear() {
                print("on Appear called ")
                viewModel.fetchAlarms()
            }
            .navigationTitle("Alarm")
            .toolbar {
                
                Button(action: {
                    showTimePicker.toggle()
                }, label: {
                    Image(systemName: "plus")
                })
            }
            .sheet(isPresented: $showTimePicker) {
                DatePickerSheet(viewModel: viewModel)
                    .presentationDetents([.medium])
                    .presentationCornerRadius(50)
                    .presentationDragIndicator(.visible)
            }
        }
	}
}

struct DatePickerSheet: View {
    
    @Environment(\.dismiss) private var dismiss
    @ObservedObject var viewModel: AlarmListViewModel
    @State private var selectedTime = Date()

    var body: some View {
        
        VStack{
            
            HStack(alignment: .top) {
                Button(action: {
                    dismiss()
                }){
                    Text("Cancel")
                }
                
                Spacer()
                
                Button(action: {
                    dismiss()
                    viewModel.addAlarm(time: selectedTime)
                    
                }){
                    Text("Save")
                }
            }
            .padding()
            
            DatePicker("", selection: $selectedTime, displayedComponents: .hourAndMinute)
                .labelsHidden()
                .datePickerStyle(.wheel)
        }
    }
}

@available(iOS 17.0, *)
struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
