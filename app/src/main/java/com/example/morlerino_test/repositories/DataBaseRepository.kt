package com.example.morlerino_test.repositories

import androidx.lifecycle.LiveData
import com.example.morlerino_test.App
import com.example.morlerino_test.MainActivity
import com.example.morlerino_test.data.GameState
import com.example.morlerino_test.models.Complexity
import kotlinx.coroutines.flow.Flow

class DataBaseRepository {

    val gameState: LiveData<GameState> = MainActivity.database.gameStateDao().get()

    fun setGameState(gameState: GameState) = MainActivity.database.gameStateDao().insert(gameState)
}