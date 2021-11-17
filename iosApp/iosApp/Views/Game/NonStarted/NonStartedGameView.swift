import SwiftUI
import shared

struct NonStartedGameView: View {
  @EnvironmentObject
  var model: MainScreenStoreModel

  let state: MainGameState.NonStarted

  var body: some View {
    VStack {
      ScrollView {
        PlayersEditView()
      }
      HStack {
        Button(
          "Start game",
          action: { model.store.onStartGameClicked() }
        )
          .disabled(!state.isStartButtonEnabled)
          .buttonStyle(.borderedProminent)
          .frame(maxWidth: .infinity)
        NavigationLink(destination: HighScoresView()) {
          Text("High Scores")
        }
        .buttonStyle(.bordered)
        .frame(maxWidth: .infinity)
      }
      .frame(maxWidth: .infinity)
      .padding(.vertical)
      .background(Material.thin)
      .controlSize(.large)
    }
  }
}

struct NonStartedGameView_Previews: PreviewProvider {
  static var previews: some View {
    Group {
      NonStartedGameView(state: .init(isStartButtonEnabled: true))
      NonStartedGameView(state: .init(isStartButtonEnabled: false))
        .preferredColorScheme(.dark)
        .previewInterfaceOrientation(.landscapeLeft)
    }
    .environmentObject(MainScreenStoreModel())
  }
}
