import SwiftUI
import shared

extension Player {
  private func title() -> String {
    switch self {
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

  func color() -> Color {
    switch self {
    case is BlueTeam:
      return .blue
    case is RedTeam:
      return .red
    default:
      return .primary
    }
  }
}
