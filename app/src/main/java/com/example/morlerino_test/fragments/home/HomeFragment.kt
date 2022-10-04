package com.example.morlerino_test.fragments.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.morlerino_test.gameViewLogic.GameActivity
import com.example.morlerino_test.R
import com.example.morlerino_test.databinding.FragmentHomeBinding
import com.example.morlerino_test.utils.defaultTextAbatement
import com.example.morlerino_test.utils.defaultTextAppearance

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeFragmentViewModel>()

    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        handler = Handler.createAsync(requireActivity().mainLooper)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.root.forEach { it.startAnimation(defaultTextAppearance()) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.gameState.observe(
            viewLifecycleOwner
        ) { it -> binding.bestScore.text = it?.bestScore?.toString() ?: "0"}
        binding.settingsButton.setOnClickListener { settingsButtonAction() }
        binding.playButton.setOnClickListener { startPlayButtonAction() }
    }

    private fun startPlayButtonAction() {
        binding.root.forEach {
            it.startAnimation(defaultTextAbatement())
        }
        val intent = Intent(context, GameActivity::class.java)
        handler.postDelayed(Runnable {startActivity(intent)},
            defaultTextAppearance().duration)
    }

    private fun settingsButtonAction() {
        binding.root.forEach {
            it.startAnimation(defaultTextAbatement())
        }
        handler.postDelayed(Runnable {
            findNavController().navigate(R.id.action_home_to_settings) },
            defaultTextAppearance().duration)
    }
}