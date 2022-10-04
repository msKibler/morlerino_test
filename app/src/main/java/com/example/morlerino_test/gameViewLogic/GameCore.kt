package com.example.morlerino_test.gameViewLogic

import com.example.morlerino_test.R
import com.example.morlerino_test.databinding.ActivityGameBinding
import com.example.morlerino_test.models.Coordinates
import com.example.morlerino_test.models.Direction
import com.example.morlerino_test.models.PlayerShip

class GameCore(val binding: ActivityGameBinding, activity: GameActivity) {

    var nextMove: () -> Unit = {}

    val playerShipDrawer = PlayerShipDrawer(binding, activity)
    private val elementDrawer = ElementDrawer(binding, activity)
    private lateinit var elementsThread: Thread
    private val imageResources = listOf<Int>(R.drawable.shot, R.drawable.meteorite)

    private fun getElementsThread(playerShip: PlayerShip) = Thread {
        while (true) {
            Thread.sleep(500)
            elementDrawer.makeElement(
                PlayerShip(
                Coordinates(binding.player.top,
                            binding.player.left),
                3),
                imageResources.random())
        }
    }



    private val playerMovementThread =  Thread{
        while (true) {
            Thread.sleep(33)
            nextMove()
        }
    }

    fun startTheGame() {
        playerMovementThread.start()
        elementsThread = getElementsThread(playerShipDrawer.playerShip)
        elementsThread.start()
    }

}