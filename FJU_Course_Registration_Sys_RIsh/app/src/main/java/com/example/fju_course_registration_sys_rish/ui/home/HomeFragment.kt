package com.example.fju_course_registration_sys_rish.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.R
import com.example.fju_course_registration_sys_rish.UserData
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapUser
import com.superlht.htloading.view.HTLoading
import org.json.JSONArray

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//
        getinfo(root)

        return root
    }

    private fun getinfo(root:View){

        val info = root.findViewById(R.id.infoPage) as LinearLayout
        val credit = root.findViewById(R.id.credit) as TextView
        val selectCourse = root.findViewById(R.id.selectCourse) as TextView
        val avgGrade = root.findViewById(R.id.avgGrade) as TextView

        Log.i("info000","here")
        val url = "http://vm.rish.com.tw/db/v1/users/"+ldapUser
        val que = Volley.newRequestQueue(info.context)
        val reqGra = object : JsonObjectRequest(Request.Method.GET, url,null,
                Response.Listener { response ->

                    Log.i("info000",response.toString())
                    credit.setText(response.getString("complete_point"))
                    selectCourse.setText(response.getString("complete_course"))
                    avgGrade.setText(response.getString("avg_score"))

                },
                Response.ErrorListener { error ->
                    Log.i("info000","fuck")
//                    getinfo(root)
                })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                val authorization = "Digest " + UserData.ldapToken
                headers["Authorization"] = authorization
                return headers
            }
        }
        que.add(reqGra)
    }

}