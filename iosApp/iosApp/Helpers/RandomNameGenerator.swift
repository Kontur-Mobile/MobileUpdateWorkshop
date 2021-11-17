import Foundation

final class RandomNameGenerator {
  private var words: [String] = [
    "Alfa",
    "Bravo",
    "Charlie",
    "Delta",
    "Echo",
    "Foxtrot",
    "Golf",
    "Hotel",
    "India",
    "Juliett",
    "Kilo",
    "Lima",
    "Mike",
    "November",
    "Oscar",
    "Papa",
    "Quebec",
    "Romeo",
    "Sierra",
    "Tango",
    "Uniform",
    "Victor",
    "Whiskey",
    "X-Ray",
    "Yankee",
    "Zulu",
  ]

  static let shared = RandomNameGenerator()

  private init() {}

  func generate() -> String {
    let index = (Int(0)..<words.count).randomElement()!
    return words[index] + "\((0..<1000).randomElement()!)"
  }
}
