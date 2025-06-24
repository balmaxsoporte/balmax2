package com.example.balmax2.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class ArrendatarioRepository(context: Context) {

    companion object {
        const val TABLE_ARRENDATARIOS = "arrendatarios"
        const val COLUMN_ID = "_id"
        const val COLUMN_PLATE = "plate"

        const val CREATE_TABLE = ("CREATE TABLE $TABLE_ARRENDATARIOS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_PLATE TEXT UNIQUE)")
    }

    private val dbHelper = AppDatabase(context.applicationContext)
    private lateinit var database: SQLiteDatabase

    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }

    fun addArrendatario(plate: String) {
        val values = ContentValues().apply {
            put(COLUMN_PLATE, plate)
        }
        database.insert(TABLE_ARRENDATARIOS, null, values)
    }

    fun getAllArrendatarios(): List<String> {
        val plates = mutableListOf<String>()
        val cursor = database.query(TABLE_ARRENDATARIOS, null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val plate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATE))
            plates.add(plate)
        }

        cursor.close()
        return plates
    }

    fun isArrendatario(plate: String): Boolean {
        val cursor = database.query(
            TABLE_ARRENDATARIOS,
            null,
            "$COLUMN_PLATE = ?",
            arrayOf(plate),
            null, null, null
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
}
