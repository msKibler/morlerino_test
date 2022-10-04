package com.example.morlerino_test.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [GameState::class], version = 5)
abstract class DataBase : RoomDatabase() {
    abstract fun gameStateDao(): GameStateDao
}