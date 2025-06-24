package com.example.balmax2.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserRepository(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Balmax2.db"
        const val DATABASE_VERSION = 1

        const val TABLE_USERS = "users"
        const val COLUMN_ID = "_id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_EDIT_USERS = "can_edit_users"
        const val COLUMN_EDIT_SPOTS = "can_edit_spots"
        const val COLUMN_EDIT_PATIO_LICENSES = "can_edit_patio_licenses"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_USERNAME TEXT,"
                + "$COLUMN_PASSWORD TEXT,"
                + "$COLUMN_EDIT_USERS INTEGER,"
                + "$COLUMN_EDIT_SPOTS INTEGER,"
                + "$COLUMN_EDIT_PATIO_LICENSES INTEGER)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, user.username)
            put(COLUMN_PASSWORD, user.password)
            put(COLUMN_EDIT_USERS, if (user.canEditUsers) 1 else 0)
            put(COLUMN_EDIT_SPOTS, if (user.canEditSpots) 1 else 0)
            put(COLUMN_EDIT_PATIO_LICENSES, if (user.canEditPatioLicenses) 1 else 0)
        }
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    fun getAllUsers(): List<User> {
        val users = mutableListOf<User>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS", null)

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME))
            val password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            val canEditUsers = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EDIT_USERS)) == 1
            val canEditSpots = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EDIT_SPOTS)) == 1
            val canEditPatioLicenses = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EDIT_PATIO_LICENSES)) == 1

            users.add(User(id, username, password, canEditUsers, canEditSpots, canEditPatioLicenses))
        }

        cursor.close()
        db.close()
        return users
    }
}
