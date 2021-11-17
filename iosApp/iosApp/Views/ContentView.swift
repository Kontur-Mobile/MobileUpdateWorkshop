import SwiftUI
import shared

struct ContentView: View {

  let gameState: MainGameState

  var body: some View {
    NavigationView {
      switch gameState {
      case let .nonStarted(nonStarted):
        NonStartedGameView(state: nonStarted)
          .navigationBarTitle("KickerChamp", displayMode: .inline)
      case let .started(started):
        StartedGameView(state: started)
          .navigationBarTitle("Game ongoing", displayMode: .inline)
      case let .finished(finished):
        FinishedGameView(state: finished)
          .navigationBarTitle("Finished game", displayMode: .inline)
      }
    }
    .navigationViewStyle(.stack)
  }
}

struct ContentView_Previews: PreviewProvider {
  static var previews: some View {
    ContentView(gameState: .finished(.init(winnerTeam: .blue)))
  }
}
