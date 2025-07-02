package com.example.balmax2

import android.content.Context
import java.io.File

object FileUtils {

    fun saveRegistroToFile(context: Context, data: List<String>) {
        val file = File(context.filesDir, "registro.txt")
        file.writeText(data.joinToString("\n"))
    }

    fun saveArrendatariosToFile(context: Context, data: List<String>) {
        val file = File(context.filesDir, "arrendatarios.txt")
        file.writeText(data.joinToString("\n"))
    }

    fun readRegistroFromFile(context: Context): List<String> {
        val file = File(context.filesDir, "registro.txt")
        return if (file.exists()) file.readLines() else emptyList()
    }

    fun readArrendatariosFromFile(context: Context): List<String> {
        val file = File(context.filesDir, "arrendatarios.txt")
        return if (file.exists()) file.readLines() else emptyList()
    }
}