import SwiftUI

@main
struct iOSApp: App {
  @StateObject var mainScreenStoreModel = MainScreenStoreModel()
  @StateObject var highScoreStoreModel = HighScoreStoreModel()

  var body: some Scene {
    WindowGroup {
      ContentView(gameState: mainScreenStoreModel.gameState)
        .environmentObject(mainScreenStoreModel)
        .environmentObject(highScoreStoreModel)
    }
  }
}
