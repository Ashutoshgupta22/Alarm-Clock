import SwiftUI
import shared

struct ContentView: View {
//	let greet = Greeting().greet()

	var body: some View {
        
        VStack{
            
            
            Text("greet")
            
            Text("Hello")
            Button(action: {
                
            }, label: {
                Text("Click")
            })
            .buttonStyle(.bordered)
    
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
