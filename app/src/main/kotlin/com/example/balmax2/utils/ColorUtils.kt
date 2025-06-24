package com.example.balmax2.utils

import android.content.Context
import androidx.core.content.ContextCompat

object ColorUtils {
    fun getARRENDColor(context: Context) = ContextCompat.getColor(context, R.color.green)
    fun getPARTColor(context: Context) = ContextCompat.getColor(context, R.color.blue)
    fun getDefaultColor(context: Context) = ContextCompat.getColor(context, R.color.white)
}
