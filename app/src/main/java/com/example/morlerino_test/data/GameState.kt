package com.example.morlerino_test.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class GameState(
    @ColumnInfo(defaultValue = "0")
    val complexity: Int,
    @ColumnInfo(defaultValue = "0")
    val bestScore: Int
) {
    @PrimaryKey
    var key = 1
}
