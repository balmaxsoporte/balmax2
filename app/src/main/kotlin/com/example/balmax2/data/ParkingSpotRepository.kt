package com.example.balmax2.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class ParkingSpotRepository(context: Context) {

    companion object {
        const val TABLE_SPOTS = "spots"
        const val COLUMN_ID = "_id"
        const val COLUMN_PATIO_NUMBER = "patio_number"
        const val COLUMN_SPOT_NUMBER = "spot_number"
        const val COLUMN_VEHICLE_TYPE = "vehicle_type"
        const val COLUMN_PLATE = "plate"
        const val COLUMN_TRAILER_TYPE = "trailer_type"

        const val CREATE_TABLE = ("CREATE TABLE $TABLE_SPOTS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_PATIO_NUMBER INTEGER,"
                + "$COLUMN_SPOT_NUMBER INTEGER,"
                + "$COLUMN_VEHICLE_TYPE TEXT,"
                + "$COLUMN_PLATE TEXT,"
                + "$COLUMN_TRAILER_TYPE TEXT)")
    }

    private val dbHelper = AppDatabase(context.applicationContext)
    private lateinit var database: SQLiteDatabase

    fun open() {
        database = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }

    fun addSpot(spot: ParkingSpot): Long {
        val values = ContentValues().apply {
            put(COLUMN_PATIO_NUMBER, spot.patioNumber)
            put(COLUMN_SPOT_NUMBER, spot.number)
            put(COLUMN_VEHICLE_TYPE, spot.vehicleType)
            put(COLUMN_PLATE, spot.plate)
            put(COLUMN_TRAILER_TYPE, spot.trailerType)
        }
        return database.insert(TABLE_SPOTS, null, values)
    }

    fun getAllSpots(): List<ParkingSpot> {
        val spots = mutableListOf<ParkingSpot>()
        val cursor = database.query(TABLE_SPOTS, null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val patioNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PATIO_NUMBER))
            val spotNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SPOT_NUMBER))
            val vehicleType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_TYPE))
            val plate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATE))
            val trailerType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRAILER_TYPE))

            spots.add(ParkingSpot(id.toInt(), spotNumber, vehicleType, trailerType, plate, patioNumber))
        }

        cursor.close()
        return spots
    }

    fun getSpotsByPatio(patioNumber: Int): List<ParkingSpot> {
        val spots = mutableListOf<ParkingSpot>()
        val selection = "$COLUMN_PATIO_NUMBER = ?"
        val selectionArgs = arrayOf(patioNumber.toString())

        val cursor = database.query(TABLE_SPOTS, null, selection, selectionArgs, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val spotNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SPOT_NUMBER))
            val vehicleType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_TYPE))
            val plate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLATE))
            val trailerType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRAILER_TYPE))

            spots.add(ParkingSpot(id.toInt(), spotNumber, vehicleType, trailerType, plate, patioNumber))
        }

        cursor.close()
        return spots
    }
}
