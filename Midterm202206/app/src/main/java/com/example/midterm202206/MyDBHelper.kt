package com.example.midterm202206

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//自訂建構子，只需傳入一個CONTEXT即可
class MyDBHelper (context: Context, name: String = database, factory:
SQLiteDatabase.CursorFactory? = null, version:Int = v):
SQLiteOpenHelper(context, name, factory, version){  //繼承SQLiteOpenHelper 類別
    companion object{
        private const val database = "mdatabase.db"
        private const val v =1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE myTable(adname text PRIMARY KEY,adpassword integer NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS myTable")
        onCreate(db)
    }
}