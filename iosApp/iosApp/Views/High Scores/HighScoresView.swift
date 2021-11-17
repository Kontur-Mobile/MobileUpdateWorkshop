import SwiftUI
import shared

struct HighScoresView: View {
  @EnvironmentObject
  var model: HighScoreStoreModel

  var body: some View {
    HighScoresList(scores: model.state.playerScores)
  }
}

struct HighScoresList: View {
  let scores: [OrderedPlayerScores]

  var body: some View {
    ScrollView {
      LazyVGrid(
        columns: [
          .init(.flexible(minimum:10, maximum: 40), alignment: .leading),
          .init(.flexible(minimum: 10, maximum: .infinity), alignment: .leading),
          .init(.flexible(minimum:20, maximum: 100), alignment: .trailing),
          .init(.flexible(minimum:20, maximum: 100), alignment: .trailing)
        ],
        spacing: 16
      ) {
        Text("#")
        Text("Игрок")
        Text("W")
        Text("GD")
        ForEach(scores) { score in
          Group {
            Text("\(score.position)")
              .font(.body.monospacedDigit())
            Text(score.name)
              .font(.headline)
            Text("\(score.wins)")
              .font(.body.monospacedDigit())
            Text("\(score.goalsDiff > 0 ? "+" : "")\(score.goalsDiff)")
              .font(.body.monospacedDigit())
          }
        }
      }
      .padding()
      .dynamicTypeSize(.xxxLarge)
    }
  }

}

struct HighScoresView_Previews: PreviewProvider {
  static let scores: [OrderedPlayerScores] = [
    .init(
      position: 1,
      name: "Wow",
      wins: 3220,
      goalsDiff: 95
    ),
    .init(
      position: 2,
      name: "Wow",
      wins: 110,
      goalsDiff: 90
    ),
    .init(
      position: 3,
      name: "Long name",
      wins: 1,
      goalsDiff: -18
    )
  ]
  static var previews: some View {
    Group {
      HighScoresList(scores: scores)
        .previewInterfaceOrientation(.landscapeLeft)
      HighScoresList(scores: scores)
        .environment(\.sizeCategory, .accessibilityExtraExtraExtraLarge)
    }.previewLayout(.sizeThatFits)
  }
}
