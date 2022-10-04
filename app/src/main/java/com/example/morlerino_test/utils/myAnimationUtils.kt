package com.example.morlerino_test.utils

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.morlerino_test.R

fun Fragment.defaultTextAppearance(): Animation = AnimationUtils.loadAnimation(
    requireContext(),
    R.anim.default_text_appearance)

fun Fragment.defaultTextAbatement(): Animation = AnimationUtils.loadAnimation(
    requireContext(),
    R.anim.default_text_abatement)

