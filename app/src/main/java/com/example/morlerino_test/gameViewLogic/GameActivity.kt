package com.example.morlerino_test.gameViewLogic

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.morlerino_test.R

import com.example.morlerino_test.databinding.ActivityGameBinding
import com.example.morlerino_test.fragments.home.HomeFragmentViewModel
import com.example.morlerino_test.models.Direction


class GameActivity : AppCompatActivity() {

    var pointsCounter = 0

    private val viewModel by viewModels<GameActivityViewModel>()
    private lateinit var gameCore: GameCore
    private lateinit var handler: Handler
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = Handler.createAsync(Looper.getMainLooper())
        binding = ActivityGameBinding.inflate(layoutInflater)
        gameCore = GameCore(binding, this)
        gameCore.startTheGame()
        binding.rightButton.setOnClickListener {
            gameCore.nextMove = {gameCore.playerShipDrawer.move(Direction.RIGHT)}
        }
        binding.leftButton.setOnClickListener {
            gameCore.nextMove = {gameCore.playerShipDrawer.move(Direction.LEFT)}
        }
        binding.player.startAnimation(playerShipAppearance())
        toolbarAnimation(toolbarAppearance())
        setContentView(binding.root)
    }

    fun gameOver() {
        viewModel.setBestScore(pointsCounter)
    }

    private fun toolbarAnimation(animation: Animation) {
        binding.gameToolbar.startAnimation(animation)
        binding.cardView.startAnimation(animation)
    }

    private fun playerShipAbatementPreparing(): Animation = AnimationUtils.loadAnimation(
        this,
        R.anim.player_ship_abatement_preparing
    )

    private fun playerShipAbatementAcceleration(): Animation = AnimationUtils.loadAnimation(
        this,
        R.anim.player_ship_abatement_acceleration
    )

    private fun playerShipAppearance(): Animation = AnimationUtils.loadAnimation(
        this,
        R.anim.player_ship_appearance
    )

    private fun toolbarAbatement(): Animation = AnimationUtils.loadAnimation(
        this,
        R.anim.game_toolbar_abatement
    )

    private fun toolbarAppearance(): Animation = AnimationUtils.loadAnimation(
        this,
        R.anim.game_toolbar_appearance
    )

    override fun onBackPressed() {
        gameCore.nextMove = {}
        toolbarAnimation(toolbarAbatement())
        binding.player.startAnimation(playerShipAbatementPreparing())
        handler.postDelayed({ binding.player.startAnimation(playerShipAbatementAcceleration())},
            playerShipAbatementPreparing().duration)
        handler.postDelayed({ super.onBackPressed() },
            playerShipAbatementAcceleration().duration + playerShipAbatementPreparing().duration - 100)
    }
}