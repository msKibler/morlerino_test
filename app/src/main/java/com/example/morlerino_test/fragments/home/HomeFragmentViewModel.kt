package com.example.morlerino_test.fragments.home

import androidx.lifecycle.*
import com.example.morlerino_test.data.GameState
import com.example.morlerino_test.repositories.DataBaseRepository
import kotlinx.coroutines.flow.Flow

class HomeFragmentViewModel : ViewModel() {
    private val mutableGameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState> = mutableGameState
    private val dataBaseRepository = DataBaseRepository()

    init {
        dataBaseRepository.gameState.observeForever(
            Observer { it -> mutableGameState.value = it }
        )
    }
}