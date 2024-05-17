import SwiftUI
import shared

struct ContentView: View {

    struct Person: Identifiable {
      let id = UUID() // Unique identifier for each person
      let name: String
      let age: Int
      var male: Bool
    }

    @State var people = [
      Person(name: "Alice", age: 30, male: false),
      Person(name: "Bob", age: 25, male: true)
    ]

	var body: some View {
      
        NavigationStack() {
            
            List{
                ForEach($people) { $person in

                    HStack{
                        VStack{
                            Text(person.name)
                            Text(String(person.age))
                        }
                        Toggle(isOn: $person.male) {
                            Text("")
                        }
                    }
                }
            }
            .navigationTitle("Alarm")
//            .toolbarBackground(.ultraThinMaterial, for: .tabBar)
            .toolbar(content: {
                
                Button(action: /*@START_MENU_TOKEN@*/{}/*@END_MENU_TOKEN@*/, label: {
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
