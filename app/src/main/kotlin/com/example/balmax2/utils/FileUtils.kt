package com.example.balmax2.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

object FileUtils {

    fun exportArrendatariosToFile(context: Context, arrendatarios: List<String>) {
        val file = File(context.filesDir, "arrendatarios.txt")
        FileOutputStream(file).use { fos ->
            OutputStreamWriter(fos).use { writer ->
                for (plate in arrendatarios) {
                    writer.write("\"$plate\"\n")
                }
            }
        }
    }

    fun exportRegistroToFile(context: Context, registros: List<String>) {
        val file = File(context.filesDir, "registro.txt")
        FileOutputStream(file).use { fos ->
            OutputStreamWriter(fos).use { writer ->
                for (plate in registros) {
                    writer.write("\"$plate\"\n")
                }
            }
        }
    }
}
