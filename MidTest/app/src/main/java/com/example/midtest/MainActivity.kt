package com.example.midtest

import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.midtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor
    lateinit var adapter:SimpleCursorAdapter
    var regex  = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var helper = MyHelper(applicationContext)
        db = helper.readableDatabase
        rs = db.rawQuery("SELECT * FROM TEST LIMIT 20", null)

        adapter = SimpleCursorAdapter(
            applicationContext, android.R.layout.simple_expandable_list_item_2, rs,
            arrayOf("user", "email","pass"), intArrayOf(android.R.id.text1, android.R.id.text2), 0
        )


        binding.loginbtn.setOnClickListener {
            var cv = ContentValues()
            val email = binding.email.text.toString()
            val username = binding.username.text.toString()
            val pass = binding.password.text.toString()
            val cfpass = binding.cfpassword.text.toString()

            cv.put("user", binding.email.text.toString())
            cv.put("email", binding.username.text.toString())
            cv.put("pass", binding.password.text.toString())

            if(email == "") {
                binding.email.error = "Vui lòng nhập email"
            }else
                if(username == "") {
                    binding.username.error = "Vui lòng nhập tên đăng nhập"

                }else
                    if(pass == "") {
                        binding.password.error = "Vui lòng nhập mật khẩu"

                    }else if(cfpass== "") {
                        binding.cfpassword.error = "Vui lòng nhập lại mật khẩu"

                    }
                    else if(pass!=cfpass) {
                        binding.cfpassword.error = "Nhập sai mật khẩu"

                    }else
                        if(email.matches(regex)) {
                            rs = db.rawQuery("SELECT * FROM TEST WHERE email = '$email'", null)
                            if (rs.getCount() > 0) {
                                Toast.makeText(this, "Email đã tồn tại", Toast.LENGTH_SHORT).show()
                            }
                        }





            db.insert("TEST", null, cv)
            rs.requery()
            adapter.notifyDataSetChanged()

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Đăng Ký")
            ad.setMessage("Đăng Ký thành công")
            ad.setPositiveButton("OK",DialogInterface.OnClickListener { dialogInterface, i ->

            })
            ad.show()

        }




        }

    }

