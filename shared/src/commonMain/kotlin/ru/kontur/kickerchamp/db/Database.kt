package ru.kontur.kickerchamp.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.kontur.kickerchamp.PlayerScore

class Database(driverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(driverFactory.createDriver())
    private val dbQueries = database.playerScoreQueries

    fun observePlayerScores(): Flow<List<PlayerScore>> {
        return dbQueries.selectAllByRating().asFlow().mapToList()
    }

    suspend fun getPlayerScores(): List<PlayerScore> {
        return withContext(Dispatchers.Default) {
            dbQueries.selectAll().executeAsList()
        }
    }

    suspend fun savePlayerScores(list: List<PlayerScore>) {
        withContext(Dispatchers.Default) {
            dbQueries.transaction {
                list.forEach { player ->
                    dbQueries.insert(player)
                }
            }
        }
    }
}