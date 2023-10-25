package com.example.day2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
         const val DATABASE_NAME = "UserAccounts.db"
         const val DATABASE_VERSION = 1
         const val TABLE_NAME = "user_accounts"
         const val COLUMN_ID = "id"
         const val COLUMN_EMAIL = "email"
         const val COLUMN_PASSWORD = "password"
         const val COLUMN_USER_TYPE = "user_type"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_PASSWORD TEXT,
                $COLUMN_USER_TYPE TEXT
            )
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades if needed
    }

    fun insertUser(email: String, password: String, userType: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_PASSWORD, password)
        values.put(COLUMN_USER_TYPE, userType)
        val result = db.insert(TABLE_NAME, null, values)
        Log.d(COLUMN_EMAIL, "User inserted successfully")
        return result != -1L
    }
}
