package com.example.balmax2.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Balmax2.db"
        const val DATABASE_VERSION = 1

        // Tabla Usuarios
        const val TABLE_USERS = "users"
        const val COLUMN_ID = "_id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_EDIT_USERS = "can_edit_users"
        const val COLUMN_EDIT_SPOTS = "can_edit_spots"
        const val COLUMN_EDIT_PATIO_LICENSES = "can_edit_patio_licenses"

        // Tabla Puestos
        const val TABLE_SPOTS = "spots"
        const val COLUMN_PATIO_NUMBER = "patio_number"
        const val COLUMN_SPOT_NUMBER = "spot_number"
        const val COLUMN_VEHICLE_TYPE = "vehicle_type"
        const val COLUMN_PLATE = "plate"
        const val COLUMN_TRAILER_TYPE = "trailer_type"

        // Tabla Arrendatarios
        const val TABLE_ARRENDATARIOS = "arrendatarios"
        const val COLUMN_ARRENDATARIO_PLATE = "plate"

        // Queries de creación de tablas
        const val CREATE_TABLE_USERS = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_USERNAME TEXT UNIQUE,"
                + "$COLUMN_PASSWORD TEXT,"
                + "$COLUMN_EDIT_USERS INTEGER DEFAULT 0,"
                + "$COLUMN_EDIT_SPOTS INTEGER DEFAULT 0,"
                + "$COLUMN_EDIT_PATIO_LICENSES INTEGER DEFAULT 0)")

        const val CREATE_TABLE_SPOTS = ("CREATE TABLE $TABLE_SPOTS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_PATIO_NUMBER INTEGER,"
                + "$COLUMN_SPOT_NUMBER INTEGER,"
                + "$COLUMN_VEHICLE_TYPE TEXT,"
                + "$COLUMN_PLATE TEXT,"
                + "$COLUMN_TRAILER_TYPE TEXT)")

        const val CREATE_TABLE_ARRENDATARIOS = ("CREATE TABLE $TABLE_ARRENDATARIOS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_ARRENDATARIO_PLATE TEXT UNIQUE)")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USERS)
        db?.execSQL(CREATE_TABLE_SPOTS)
        db?.execSQL(CREATE_TABLE_ARRENDATARIOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_SPOTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ARRENDATARIOS")
        onCreate(db)
    }
}