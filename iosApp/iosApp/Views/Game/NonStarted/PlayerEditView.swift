import SwiftUI
import shared

struct PlayerEditView: View {
  let player: Player
  let randomize: () -> Void
  @Binding var name: String

  var body: some View {
    VStack(alignment: .leading) {
      HStack {
        Text(title(for: player))
          .foregroundColor(color(for: player))
#if DEBUG
        Spacer()
        Button(
          "🎲",
          action: randomize
        )
          .buttonStyle(.bordered)
          .controlSize(.mini)
#endif
      }
      TextField("Name", text: $name, prompt: Text("Name"))
        .textFieldStyle(.roundedBorder)
    }
  }

  private func title(for: Player) -> String {
    switch player {
    case is BlueDefender:
      return "🥅🔵 Blue Defender"
    case is BlueForward:
      return "⚔️🔵 Blue Forward"
    case is RedDefender:
      return "🥅🔴 Red Defender"
    case is RedForward:
      return "⚔️🔴 Red Forward"
    default:
      return "Unknown"
    }
  }

  private func color(for: Player) -> Color {
    switch player {
    case is BlueTeam:
      return .blue
    case is RedTeam:
      return .red
    default:
      return .primary
    }
  }
}

struct PlayerEditView_Previews: PreviewProvider {
  @State static var name: String = ""
  static var previews: some View {
    Group {
      PlayerEditView(
        player: BlueForward(name: "Johnny"),
        randomize: {},
        name: $name
      )
      PlayerEditView(
        player: RedDefender(name: "Jacky"),
        randomize: {},
        name: $name
      )
    }
    .previewLayout(.sizeThatFits)
  }
}
