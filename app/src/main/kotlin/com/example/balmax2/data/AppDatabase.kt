package com.example.balmax2.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Balmax2.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(UserRepository.CREATE_TABLE)
        db?.execSQL(ParkingSpotRepository.CREATE_TABLE)
        db?.execSQL(ArrendatarioRepository.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${UserRepository.TABLE_USERS}")
        db?.execSQL("DROP TABLE IF EXISTS ${ParkingSpotRepository.TABLE_SPOTS}")
        db?.execSQL("DROP TABLE IF EXISTS ${ArrendatarioRepository.TABLE_ARRENDATARIOS}")
        onCreate(db)
    }
}
