package com.example.morlerino_test.utils

import android.util.Log
import android.view.View
import com.example.morlerino_test.databinding.ActivityGameBinding
import com.example.morlerino_test.models.Coordinates

fun View.checkViewCanMoveThroughBorder(
    coordinates: Coordinates,
    binding: ActivityGameBinding): Boolean {
    if (coordinates.top <= binding.gameToolbar.height*2
        && coordinates.left >= 0
        && coordinates.left <= binding.root.width*2){
            return true
    }
    return false
}