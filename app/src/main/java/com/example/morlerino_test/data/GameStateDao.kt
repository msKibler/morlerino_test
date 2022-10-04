package com.example.morlerino_test.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameStateDao {

    @Query("SELECT * FROM GameState WHERE 'key' = 1")
    fun get(): LiveData<GameState>

    @Update
    fun update(gameState: GameState)

    @Insert
    fun insert(gameState: GameState)
}