package ru.kontur.kickerchamp

sealed class Player {
    abstract val name: String
}

sealed interface Team
interface BlueTeam : Team {
    companion object: BlueTeam
}
interface RedTeam : Team {
    companion object: RedTeam
}

sealed interface PlayerRole
interface Forward : PlayerRole
interface Defender : PlayerRole

data class BlueForward(override val name: String = "") : Player(), BlueTeam, Forward
data class BlueDefender(override val name: String = "") : Player(), BlueTeam, Defender
data class RedForward(override val name: String = "") : Player(), RedTeam, Forward
data class RedDefender(override val name: String = "") : Player(), RedTeam, Defender