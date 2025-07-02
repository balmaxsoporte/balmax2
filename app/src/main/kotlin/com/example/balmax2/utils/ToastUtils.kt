package com.example.balmax2.utils

import android.widget.Toast
import android.content.Context

object ToastUtils {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}