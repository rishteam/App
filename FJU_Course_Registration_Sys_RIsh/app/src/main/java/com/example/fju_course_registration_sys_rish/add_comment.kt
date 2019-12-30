package com.example.fju_course_registration_sys_rish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapToken
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapUser
import kotlinx.android.synthetic.main.activity_add_comment.*
import org.json.JSONObject

class add_comment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_comment)

        val btn = findViewById(R.id.commentBtn) as Button
        val cid = intent.getStringExtra("cid")

        val body: MutableMap<String,String> = HashMap<String, String>()

        btn.setOnClickListener {

            body["message"] = commentText.text.toString()


            val json = JSONObject(body as Map<*, *>)
            val url = "http://vm.rish.com.tw/db/v1/comments/" + ldapUser + "/" + cid
            val que = Volley.newRequestQueue(this)
            val req = object : JsonObjectRequest(Request.Method.POST, url, json,
                Response.Listener {
                        response ->
                    Toast.makeText(this@add_comment, "comment send", Toast.LENGTH_SHORT).show()
                    finish()

                }, Response.ErrorListener {
                        error ->
                    Toast.makeText(this@add_comment, "can't add comment", Toast.LENGTH_SHORT).show()
                    Log.i("commentSendErr",error.toString())
                }){

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    val authorization = "Digest " + ldapToken
                    headers["Authorization"] = authorization
                    return headers
                }
//
//                override fun getBody(): ByteArray {
//
//                    val tmp : MutableMap<String, String> = HashMap<String, String>()
//                    tmp["message"] = commentText.text.toString()
//
//                    return tmp
//                }

//                override fun getParams(): MutableMap<String, String> {
//
//                    val tmp : MutableMap<String, String> = HashMap<String, String>()
//                    tmp["message"] = commentText.text.toString()
//
//                    return tmp
//                }
            }
            que.add(req)
        }
    }
}
