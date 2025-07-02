package com.example.balmax2

import android.content.Context
import androidx.core.content.ContextCompat

object ColorUtils {

    fun getARRENDColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.arrendatario_color)
    }

    fun getPARTColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.particular_color)
    }

    fun getDefaultColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.default_color)
    }
}