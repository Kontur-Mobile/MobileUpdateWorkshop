import shared

enum MainTeam {
  case blue
  case red

  static func create(_ team: Team) -> MainTeam {
    switch team {
    case is BlueTeam: return .blue
    case is RedTeam: return .red
    default: fatalError("wrong team")
    }
  }
}

enum MainGameState: Hashable {
  struct NonStarted: Hashable {
    let isStartButtonEnabled: Bool
  }
  case nonStarted(NonStarted)
  struct Started: Hashable {
    let blueScore: Int
    let redScore: Int
  }
  case started(Started)
  struct Finished: Hashable {
    let winnerTeam: MainTeam
  }
  case finished(Finished)

  init(_ gameState: GameState) {
    if let nonStarted = gameState as? GameState.NonStarted {
      self = .nonStarted(.init(
        isStartButtonEnabled: nonStarted.isStartButtonEnabled
      ))
    } else if let started = gameState as? GameState.Started {
      self = .started(.init(
        blueScore: Int(started.blueScore),
        redScore: Int(started.redScore)
      ))
    } else if let finished = gameState as? GameState.Finished {
      self = .finished(.init(
        winnerTeam: MainTeam.create(finished.winnerTeam)
      ))
    } else {
      fatalError("Unknown sealed class instance \(gameState)")
    }
  }
}
