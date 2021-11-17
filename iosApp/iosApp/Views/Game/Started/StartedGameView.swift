import SwiftUI
import shared

struct StartedGameView: View {
  @EnvironmentObject
  var model: MainScreenStoreModel

  var state: MainGameState.Started // blueScore: Int; redScore: Int

  var body: some View {
    VStack {
      Text("Game just started. We should manage score!")
      #warning("TODO: Started game view")
    }
  }
}

struct StartedGameView_Previews: PreviewProvider {
  static var previews: some View {
    StartedGameView(state: .init(blueScore: 0, redScore: 10))
    .environmentObject(MainScreenStoreModel())
  }
}
