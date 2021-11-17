import SwiftUI
import shared

struct FinishedGameView: View {
  @EnvironmentObject
  var model: MainScreenStoreModel

  @State var animated = false

  let state: MainGameState.Finished

  var body: some View {
    VStack {
      Text(state.winnerTeam == .blue ? "Blue wins!" : "Red wins!")
        .foregroundColor(color(for: state.winnerTeam))
      Button("Start new game", action: { model.store.onRestartGameClicked() })
        .buttonStyle(.borderedProminent)
      Button("Revenge", action: { model.store.onRevengeClicked() })
        .buttonStyle(.bordered)
        .tint(color(for: state.winnerTeam))
      NavigationLink(destination: HighScoresView()) {
        Text("High scores")
      }
      .buttonStyle(.bordered)
    }
    .offset(y: animated ? 0 : 300)
    .onAppear {
      withAnimation(.easeOut(duration: 0.35)) {
        animated = true
      }
    }
  }

  private func color(for team: MainTeam) -> Color {
    switch team {
    case .blue: return Color.blue
    case .red: return Color.red
    }
  }
}

struct FinishedGameView_Previews: PreviewProvider {
  static var previews: some View {
    Group {
      FinishedGameView(state: .init(winnerTeam: .red))
      FinishedGameView(state: .init(winnerTeam: .blue))
    }
    .environmentObject(MainScreenStoreModel())
  }
}
