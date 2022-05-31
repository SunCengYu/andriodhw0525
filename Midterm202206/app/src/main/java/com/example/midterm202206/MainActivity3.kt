package com.example.midterm202206

import android.content.AbstractThreadedSyncAdapter
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class MainActivity3 :AppCompatActivity() {

    private lateinit var btn_signup: Button
    private lateinit var dbrw: SQLiteDatabase
    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>


    override fun onCreate(savedINstanceState: Bundle?) {
        super.onCreate(savedINstanceState)
        setContentView(R.layout.activity_main3)
        btn_signup = findViewById(R.id.btn_signup)
        //取得資料庫實體
        dbrw = MyDBHelper(this).writableDatabase
        //設定監聽器
        setListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbrw.close()
    }

    private fun setListener() {
        val ed_adname = findViewById<EditText>(R.id.ed_adname)
        val ed_adpassword = findViewById<EditText>(R.id.ed_adpassword)
        findViewById<Button>(R.id.btn_signup).setOnClickListener {
            //判斷是否有填入書名或價格
            if (ed_adname.length() < 1 || ed_adpassword.length() < 1)
                showToast("欄位請勿留空")
            else
                try {
                    //新增一筆書籍紀錄於 myTable 資料表
                    dbrw.execSQL(
                        "INSERT INTO myTable(adname, adpassword) VALUES(?,?)",
                        arrayOf(
                            ed_adname.text.toString(),
                            ed_adpassword.text.toString()
                        )
                    )
                    showToast("新增:${ed_adname.text},密碼:${ed_adpassword.text}")
                    cleanEditText()
                    finish()
                } catch (e: Exception) {
                    showToast("新增失敗:$e")
                    finish()
                }
        }

    }
    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
    //清空輸入的帳號與密碼
    private fun cleanEditText() {
        findViewById<EditText>(R.id.ed_adname).setText("")
        findViewById<EditText>(R.id.ed_adpassword).setText("")
    }
}