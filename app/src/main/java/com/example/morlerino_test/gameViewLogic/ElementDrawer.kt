package com.example.morlerino_test.gameViewLogic

import android.widget.FrameLayout
import android.widget.ImageView
import com.example.morlerino_test.databinding.ActivityGameBinding
import com.example.morlerino_test.models.Coordinates
import com.example.morlerino_test.models.PlayerShip
import com.example.morlerino_test.utils.checkViewCanMoveThroughBorder
import kotlin.math.abs
import kotlin.random.Random

private const val ELEMENT_WIDTH = 200
private const val ELEMENT_HEIGHT = 200

class ElementDrawer(private val binding: ActivityGameBinding, private val activity: GameActivity) {

    private var gameIsOver = false

    fun makeElement(playerShip: PlayerShip, imageResource: Int) {
        Thread {
            val element = createElement(imageResource, binding.root)
            while (element.checkViewCanMoveThroughBorder(Coordinates(element.top, element.left/2-element.width), binding) && !gameIsOver) {
                Thread.sleep(30)
                (element.layoutParams as FrameLayout.LayoutParams).topMargin += 15
                drawFrame(element)
                if (elementsAndPlayerCollided(element, playerShip)){
                    activity.gameOver()
                    gameIsOver = true
                    (binding.root.context as GameActivity).onBackPressed()
                }
            }
            activity.runOnUiThread {
                activity.pointsCounter ++
                binding.pointsCounter.text = activity.pointsCounter.toString()
                binding.root.removeView(element)
            }
        }.start()
    }

    private fun elementsAndPlayerCollided(element: ImageView, playerShip: PlayerShip): Boolean {
        if (abs((element.layoutParams as FrameLayout.LayoutParams).topMargin
                    - playerShip.coordinates.top - binding.player.height) <= binding.player.height
            && abs((element.layoutParams as FrameLayout.LayoutParams).leftMargin
                    - (binding.player.layoutParams as FrameLayout.LayoutParams).leftMargin) <= binding.player.width){
            return true
        }
        return false
    }


    private fun drawFrame(element: ImageView) {
        activity.runOnUiThread {
            binding.root.removeView(element)
            binding.root.addView(element)
        }
    }

    private fun createElement(
        imageResource: Int,
        container: FrameLayout): ImageView {
        val element = ImageView(container.context)
            .apply {
                this.layoutParams = FrameLayout.LayoutParams(ELEMENT_WIDTH, ELEMENT_HEIGHT)
                this.setImageResource(imageResource)
                (this.layoutParams as FrameLayout.LayoutParams)
                    .leftMargin += Random.nextInt(10, binding.root.width)
                }
        return element
    }

}