package com.example.midterm202206

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbrw = MyDBHelper(this).writableDatabase
       // val correct_username: String = "admin"
        val righttext = "OK!"
        val wrongtext = "Wrong username or password!"
        var toastmsg: String = "wrong"
        var username: EditText = findViewById(R.id.Name)
        var password: EditText = findViewById(R.id.Password)
        val btnlogin: Button = findViewById<Button>(R.id.button)
        var tvstatus : TextView = findViewById(R.id.tv_status)
        val btnsignin :Button = findViewById<Button>(R.id.button1)


        btnsignin.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
        btnlogin.setOnClickListener {
            tvstatus.text = ""
            val ed_adname = findViewById<EditText>(R.id.ed_adname)
            val ed_adpassword = findViewById<EditText>(R.id.ed_adpassword)

            val queryString = "SELECT * FROM myTable WHERE adname LIKE '${username.text}'"
            val c = dbrw.rawQuery(queryString, null)
            c.moveToFirst()

            //判斷是否輸入正確
            if((username.text.toString() == c.getString(0).toString()
                && password.text.toString() == c.getString(1))){
                Log.e("MainActivity2", username.text.toString())
                username.text.clear()
                password.text.clear()

                val intent = Intent(this, MainActivity2::class.java)
                val bundle = Bundle()
                bundle.putInt("key1", 111)
                bundle.putString("key2", "admin")
                intent.putExtras(bundle)
                startActivity(intent)
                //Toast.makeText(this@MainActivity, righttext , Toast.LENGTH_LONG).show()
            }else{
                tvstatus.text = "wrong username or password"
                Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "wrong username or password")
            }

        }



    }
}