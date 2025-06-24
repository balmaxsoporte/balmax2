package com.example.balmax2.utils

import android.content.Context
import java.io.*

object FileUtils {

    // Directorio donde se guardarán los archivos
    private fun getStorageDir(context: Context): File {
        return context.filesDir
    }

    // Guardar archivo arrendatarios.txt
    fun saveArrendatariosToFile(context: Context, plates: List<String>) {
        val file = File(getStorageDir(context), "arrendatarios.txt")
        try {
            val writer = BufferedWriter(FileWriter(file))
            for (plate in plates) {
                writer.write("\"$plate\"")
                writer.newLine()
            }
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Guardar archivo registro.txt
    fun saveRegistroToFile(context: Context, plates: List<String>) {
        val file = File(getStorageDir(context), "registro.txt")
        try {
            val writer = BufferedWriter(FileWriter(file))
            for (plate in plates) {
                writer.write("\"$plate\"")
                writer.newLine()
            }
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Leer archivo arrendatarios.txt
    fun readArrendatariosFromFile(context: Context): List<String> {
        val file = File(getStorageDir(context), "arrendatarios.txt")
        val plates = mutableListOf<String>()
        if (!file.exists()) return plates

        try {
            val reader = BufferedReader(FileReader(file))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let {
                    val cleanPlate = it.replace("\"", "").trim()
                    if (cleanPlate.isNotEmpty()) {
                        plates.add(cleanPlate)
                    }
                }
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return plates
    }

    // Leer archivo registro.txt
    fun readRegistroFromFile(context: Context): List<String> {
        val file = File(getStorageDir(context), "registro.txt")
        val plates = mutableListOf<String>()
        if (!file.exists()) return plates

        try {
            val reader = BufferedReader(FileReader(file))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let {
                    val cleanPlate = it.replace("\"", "").trim()
                    if (cleanPlate.isNotEmpty()) {
                        plates.add(cleanPlate)
                    }
                }
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return plates
    }

    // Verificar si una patente está en arrendatarios.txt
    fun isArrendatario(context: Context, plate: String): Boolean {
        val plates = readArrendatariosFromFile(context)
        return plates.contains(plate)
    }

    // Obtener todas las patentes registradas
    fun getAllRegistros(context: Context): List<String> {
        return readRegistroFromFile(context)
    }
}
