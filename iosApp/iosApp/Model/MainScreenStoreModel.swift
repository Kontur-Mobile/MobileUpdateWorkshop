import SwiftUI
import shared

final class MainScreenStoreModel: ObservableObject {
  let store = MainScreenStore(database: database)

  private var stateWatcher: Closeable?

  @Published
  private(set) var state: MainScreenState = .init(
    blueDefender: .init(name: ""),
    blueForward: .init(name: ""),
    redDefender: .init(name: ""),
    redForward: .init(name: ""),
    gameState: GameState.NonStarted(isStartButtonEnabled: false),
    dialogState: nil
  )

  @Published
  private(set) var gameState: MainGameState = .nonStarted(.init(
    isStartButtonEnabled: false
  ))

  init() {
    subscribe()
  }

  deinit {
    stateWatcher?.close()
  }

  private func subscribe() {
    stateWatcher = store.watchState().watch { [weak self] state in
      guard let self = self else { return }
      print("Main\r\t\(state)")
      self.state = state
      self.gameState = .init(state.gameState)
    }
  }
}
