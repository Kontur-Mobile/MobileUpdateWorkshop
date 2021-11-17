import SwiftUI
import shared

struct PlayersEditView: View {
  @EnvironmentObject
  var model: MainScreenStoreModel

  var body: some View {
    Group {
      VStack {
        Text("Blue Team")
          .font(.title)
          .padding(.bottom)
        PlayerEditView(
          player: model.state.blueDefender,
          randomize: randomize(model.state.blueDefender),
          name: binding(player: model.state.blueDefender)
        )
        PlayerEditView(
          player: model.state.blueForward,
          randomize: randomize(model.state.blueForward),
          name: binding(player: model.state.blueForward)
        )
      }.padding()
      VStack {
        Text("Red Team")
          .font(.title)
          .padding(.bottom)
        PlayerEditView(
          player: model.state.redDefender,
          randomize: randomize(model.state.redDefender),
          name: binding(player: model.state.redDefender)
        )
        PlayerEditView(
          player: model.state.redForward,
          randomize: randomize(model.state.redForward),
          name: binding(player: model.state.redForward)
        )
      }
      .padding()
    }
  }

  private func randomize(_ player: Player) -> (() -> Void) {
    {
    model.store.onPlayerNameChanged(
      player: player,
      name: RandomNameGenerator.shared.generate()
    )
    }
  }

  private func binding(player: Player) -> Binding<String> {
    .init(get: {
      player.name
    }, set: { name in
      model.store.onPlayerNameChanged(player: player, name: name)
    })
  }
}


struct PlayersEditView_Previews: PreviewProvider {
  static var previews: some View {
    PlayersEditView()
      .environmentObject(MainScreenStoreModel())
      .previewLayout(.sizeThatFits)
  }
}

