package com.example.morlerino_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.room.Room
import com.example.morlerino_test.data.DataBase
import com.example.morlerino_test.data.GameState
import com.example.morlerino_test.databinding.ActivityMainBinding
import com.example.morlerino_test.models.Complexity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(
            applicationContext,
            DataBase::class.java, "database6")
            .allowMainThreadQueries()
            .build()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        lateinit var database: DataBase
    }
}