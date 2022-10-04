package com.example.morlerino_test.fragments.settings

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.navigation.fragment.findNavController
import com.example.morlerino_test.R
import com.example.morlerino_test.databinding.FragmentSettingsBinding
import com.example.morlerino_test.utils.defaultTextAbatement
import com.example.morlerino_test.utils.defaultTextAppearance


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private val settingsToHomeAction = Runnable {
        findNavController().navigate(R.id.action_settings_to_home2)
    }

    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        handler = Handler.createAsync(requireActivity().mainLooper)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.saveSettingsButton.setOnClickListener {
            binding.root.forEach { it.startAnimation(defaultTextAbatement()) }

            handler.postDelayed(settingsToHomeAction, defaultTextAppearance().duration )
        }

        binding.root.forEach { it.startAnimation(defaultTextAppearance()) }
    }
}