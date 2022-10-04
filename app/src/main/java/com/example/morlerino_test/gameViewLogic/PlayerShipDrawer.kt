package com.example.morlerino_test.gameViewLogic

import android.widget.FrameLayout
import com.example.morlerino_test.databinding.ActivityGameBinding
import com.example.morlerino_test.models.Coordinates
import com.example.morlerino_test.models.Direction
import com.example.morlerino_test.models.PlayerShip
import com.example.morlerino_test.utils.checkViewCanMoveThroughBorder

class PlayerShipDrawer(private val binding : ActivityGameBinding,private val activity: GameActivity) {

    var playerShip = PlayerShip(Coordinates(binding.player.top, binding.player.left), 3)

    fun move(direction: Direction) {
        val layoutParams = binding.player.layoutParams as FrameLayout.LayoutParams
        val currentCoordinate = Coordinates(layoutParams.topMargin, layoutParams.leftMargin)
        when(direction){
            Direction.LEFT -> {
                (binding.player.layoutParams as FrameLayout.LayoutParams).leftMargin -= 30
            }
            Direction.RIGHT -> {
                (binding.player.layoutParams as FrameLayout.LayoutParams).leftMargin += 30
            }
            Direction.DIRECTLY -> {}
        }
        val nextCoordinates = Coordinates(
            (binding.player.layoutParams as FrameLayout.LayoutParams).topMargin,
            (binding.player.layoutParams as FrameLayout.LayoutParams).leftMargin)

        if (binding.player.checkViewCanMoveThroughBorder(
                nextCoordinates,
                binding)
        ) {
            activity.runOnUiThread{
                binding.root.removeView(binding.player)
                binding.root.addView(binding.player)
            }
        } else {
            (binding.player.layoutParams as FrameLayout.LayoutParams).topMargin = currentCoordinate.top
            (binding.player.layoutParams as FrameLayout.LayoutParams).leftMargin = currentCoordinate.left
        }
    }
}