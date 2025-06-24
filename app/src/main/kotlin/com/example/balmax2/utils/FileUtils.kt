package com.example.balmax2.utils

import android.content.Context
import java.io.File

object FileUtils {
    fun getOrCreateTxtFile(context: Context, filename: String): File {
        val folder = context.getExternalFilesDir(null)
        val file = File(folder, filename)
        if (!file.exists()) file.createNewFile()
        return file
    }
}
