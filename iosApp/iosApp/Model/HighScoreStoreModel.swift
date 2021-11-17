import SwiftUI
import shared

final class HighScoreStoreModel: ObservableObject {
  let store: HighScoreStore = HighScoreStore(database: database)

  private var stateWatcher: Closeable?

  @Published
  private(set) var state: HighScoreState = .init(
    playerScores: []
  )

  init() {
    subscribe()
  }

  deinit {
    stateWatcher?.close()
  }

  private func subscribe() {
    stateWatcher = store.watchState().watch { [weak self] state in
      print("High Score\r\t\(state)")
      self?.state = state
    }
  }
}

extension OrderedPlayerScores: Identifiable {
  public var id: Int {
    var hasher = Hasher()
    hasher.combine(name)
    hasher.combine(wins)
    hasher.combine(goalsDiff)
    return hasher.finalize()
  }
}
