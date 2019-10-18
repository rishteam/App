package com.example.fju_course_registration_sys_rish


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?){

        Log.i("Tag", "onClick")
        when(v?.id){

            R.id.Login -> toHomePage()
            R.id.Registration -> toRegisterPage()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("hello", "hello")
        Login.setOnClickListener(this)
        Registration.setOnClickListener(this)

    }

    private fun toHomePage(){

        Log.i("Tag", "toHomePage")
        Toast.makeText(this@MainActivity, "Login Success", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, Home::class.java))
        finish()
    }

    private fun toRegisterPage(){

        Log.i("Tag", "toRegisterPage")
        Toast.makeText(this@MainActivity, "Register Success", Toast.LENGTH_SHORT).show()

    }
}
