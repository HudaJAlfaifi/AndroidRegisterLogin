package com.example.day2
import android.content.Context
import android.util.Log


class UserRepository(private val context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertUser(email: String, password: String, userType: String): Boolean {
        return dbHelper.insertUser(email, password, userType)

    }

    //fun getAllUsers(): List<User> {
        //val users = mutableListOf<User>()
        //val db = dbHelper.readableDatabase
        //val cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME}", null)
        //while (cursor.moveToNext()) {
            //val id = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
            //val email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL))
            //val password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD))
            //val userType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_TYPE))
            //users.add(User(id, email, password, userType))
        //}
        //cursor.close()
        //return users
    //}
    fun checkUserExists(email: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val selection = "${DatabaseHelper.COLUMN_EMAIL} = ? AND ${DatabaseHelper.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val userExists = cursor.moveToFirst()
        cursor.close()
        return userExists
    }

}
