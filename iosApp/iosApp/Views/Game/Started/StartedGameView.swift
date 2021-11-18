import SwiftUI
import shared

struct StartedGameView: View {
  @EnvironmentObject
  var model: MainScreenStoreModel

  var state: MainGameState.Started

  var body: some View {
    VStack {
      Spacer()
      VStack {
        TeamScoreboardView(
          name: "Blue",
          color: .blue,
          onIncrement: model.store.onIncrementBlue,
          onDecrement: model.store.onDecrementBlue,
          score: state.blueScore
        )
      }
      Spacer(minLength: 16)
      VStack {
        TeamScoreboardView(
          name: "Red",
          color: .red,
          onIncrement: model.store.onIncrementRed,
          onDecrement: model.store.onDecrementRed,
          score: state.redScore
        )
      }
      Spacer()
    }
  }
}

struct StartedGameView_Previews: PreviewProvider {
  static var previews: some View {
    StartedGameView(state: .init(blueScore: 0, redScore: 10))
      .environmentObject(MainScreenStoreModel())
  }
}
