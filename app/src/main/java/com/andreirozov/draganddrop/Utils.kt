package com.andreirozov.draganddrop

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

object Utils {
    @Suppress("DEPRECATION")
    fun clickVibrate(context: Context) {
        if (Build.VERSION.SDK_INT >= 29) {
            (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
                VibrationEffect.createOneShot(
                    50,
                    VibrationEffect.EFFECT_TICK
                )
            )
        } else {
            (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(50)
        }
    }
}