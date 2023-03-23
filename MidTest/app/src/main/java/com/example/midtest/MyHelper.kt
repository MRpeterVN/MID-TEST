package com.example.midtest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper (context: Context) : SQLiteOpenHelper(context,"dbTEST",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
//   tạo table , column
        db?.execSQL("CREATE TABLE TEST(_id integer primary key autoincrement,email TEXT, user TEXT ,pass TEXT)")
//   them data
        db?.execSQL("INSERT INTO TEST(user,email,pass) values ('mot','mot@gmail.com','12334')")

    }


    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}