package com.example.rockpaperscissors.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rockpaperscissors.models.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    suspend fun getAllGames(): List<Game>

    @Query("SELECT count(*) FROM game WHERE result = 0")
    suspend fun getWinCount(): Int

    @Query("SELECT count(*) FROM game WHERE result = 1")
    suspend fun getLossCount(): Int

    @Query("SELECT count(*) FROM game WHERE result = 2")
    suspend fun getDrawCount(): Int

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM game")
    suspend fun deleteAllGames()
}