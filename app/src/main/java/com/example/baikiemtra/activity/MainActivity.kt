package com.example.baikiemtra.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.baikiemtra.R
import com.example.baikiemtra.database.UserDao
import com.example.baikiemtra.database.UserRoomDatabase
import com.example.baikiemtra.model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var userDB:UserRoomDatabase ?=null
    private lateinit var userDao:UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDB = UserRoomDatabase.getDatabase(this)
        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val email = editEmail.text.toString()
        val userName = editUserName.text.toString()
        val password = editPassword.text.toString()
        val confirm = confirmPassword.text.toString()
        if(email.isNotEmpty()&&userName.isNotEmpty()&&password.isNotEmpty()){
            if(confirm==password){
                val user = User(null,email, userName, password)
                GlobalScope.launch(Dispatchers.IO) {
                    userDB?.userDao()?.register(user)

                }
                editEmail.text.clear()
                editUserName.text.clear()
                editPassword.text.clear()
                confirmPassword.text.clear()
                Toast.makeText(this,"Đăng kí thành công",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"Nhập mật khẩu xác nhận đúng",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Vui lòng điền đầy đủ",Toast.LENGTH_SHORT).show()
        }
    }
}