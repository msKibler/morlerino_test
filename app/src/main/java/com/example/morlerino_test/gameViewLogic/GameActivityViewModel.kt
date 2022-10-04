package com.example.morlerino_test.gameViewLogic

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.morlerino_test.data.GameState
import com.example.morlerino_test.models.Complexity
import com.example.morlerino_test.repositories.DataBaseRepository
import kotlinx.coroutines.launch
import kotlin.math.max

class GameActivityViewModel : ViewModel() {

    private val dataBaseRepository = DataBaseRepository()

    fun setBestScore(score: Int) {
        dataBaseRepository.setGameState(
            GameState(
                dataBaseRepository.gameState.value?.complexity ?: Complexity.EASY.value,
                max(dataBaseRepository.gameState.value?.bestScore ?: 0, score)
            )
        )
    }
}