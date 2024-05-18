import SwiftUI
import shared

struct ContentView: View {

//    struct Person: Identifiable {
//      let id = UUID() // Unique identifier for each person
//      let name: String
//      let age: Int
//      var male: Bool
//    }
//    
    @ObservedObject private var alarmListViewModel = AlarmListViewModel()
    @State private var selectedTime = Date()

//    @State var people = [
//      Person(name: "Alice", age: 30, male: false),
//      Person(name: "Bob", age: 25, male: true)
//    ]

	var body: some View {
      
        NavigationStack() {
            
            List{
                ForEach($alarmListViewModel.alarmList, id: \.self) { $alarm in

                    HStack{
                        VStack{
                            Text(String(alarm.hour))
                            Text(String(alarm.minute))
                        }
                        Toggle(isOn: $alarm.isSet) {
                            Text("")
                        }
                    }
                }
            }
            .navigationTitle("Alarm")
//            .toolbarBackground(.ultraThinMaterial, for: .tabBar)
            .toolbar(content: {
                
                Button(action: {
                    DatePicker("Select Time", selection: $selectedTime, displayedComponents:
                            .hourAndMinute).labelsHidden()
                }, label: {
                    Image(systemName: "plus")
                })
//                .background(.ultraThinMaterial)
            })
        }
//        .navigationTitle("Alarm")
//        .toolbarBackground(.ultraThickMaterial, for: .tabBar)
//        .toolbar(content: {
//            
//            Button(action: /*@START_MENU_TOKEN@*/{}/*@END_MENU_TOKEN@*/, label: {
//                /*@START_MENU_TOKEN@*/Text("Button")/*@END_MENU_TOKEN@*/
//            })
//            .background(.ultraThinMaterial)
//        })
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
