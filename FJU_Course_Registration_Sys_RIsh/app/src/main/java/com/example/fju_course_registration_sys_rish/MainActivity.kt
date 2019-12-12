package com.example.fju_course_registration_sys_rish


import android.content.Context
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
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapToken
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import org.json.JSONArray
import org.json.JSONObject
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?){

        Log.i("Tag", "onClick")
        when(v?.id){

            R.id.Login -> toHomePage()
            R.id.fordbg -> toHome()

        }

        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Login.setOnClickListener(this)
        fordbg.setOnClickListener(this)
        Login_page.setOnClickListener(this)

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

                ldapToken = response.getString("token")
                ldapUser = account
                Log.i("tokenget", ldapUser + " " + ldapToken)
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


    private fun toHome(){

        Toast.makeText(this@MainActivity, "For Debug Login", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, Home::class.java))
        finish()

    }

}
