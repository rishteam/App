package com.example.fju_course_registration_sys_rish


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?){

        Log.i("Tag", "onClick")
        when(v?.id){

            R.id.Login -> toHomePage()
//            R.id.Registration -> toRegisterPage()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Login.setOnClickListener(this)
//        Registration.setOnClickListener(this)

    }

    private fun toHomePage(){

        Log.i("Tag", "toHomePage")
        val account = Account.text.toString()
        val password = PWD.text.toString()

        val body: MutableMap<String,String> = HashMap<String, String>()
        body["username"] = account
        body["password"] = password
        val json = JSONObject(body as Map<*, *>)

        Log.i("Login",body.toString())
        val url = "http://vm.rish.com.tw/db/v1/login"
        val que = Volley.newRequestQueue(this)
        val req = object : JsonObjectRequest(Request.Method.POST, url, json,
            Response.Listener {
                    response ->
                Log.i("Login","Success")
                Log.i("Login",response.toString())

                val token = response.getString("token")
                Log.i("token",token)

                Toast.makeText(this@MainActivity, "Login Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Home::class.java))
                finish()

            }, Response.ErrorListener {
                    error ->
                Toast.makeText(this@MainActivity, "Login Failed", Toast.LENGTH_SHORT).show()
                Log.i("Login","Fuck")
            }){}

        que.add(req)

    }


//    private fun toRegisterPage(){
//
//        Log.i("Tag", "toRegisterPage")
//        Toast.makeText(this@MainActivity, "Register Success", Toast.LENGTH_SHORT).show()
//
//    }

}
