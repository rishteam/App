package com.example.fju_course_registration_sys_rish.ui.curriculum

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
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.R
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapToken
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapUser
import kotlinx.android.synthetic.main.fragment_curriculum.*
import kotlinx.android.synthetic.main.fragment_send.view.*
import org.json.JSONArray

class CurriculumFragment : Fragment() {

    private lateinit var curriculumViewModel: CurriculumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        curriculumViewModel =
            ViewModelProviders.of(this).get(CurriculumViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_curriculum, container, false)
//        val textView: TextView = root.findViewById(R.id.text_curriculum)
//        curriculumViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

        val courseText : MutableList<MutableList<TextView>> = arrayListOf()
        val courseId = listOf(
            listOf(R.id.Mon_1, R.id.Mon_2,R.id.Mon_3,R.id.Mon_4, R.id.Mon_5,R.id.Mon_6,R.id.Mon_7, R.id.Mon_8,R.id.Mon_9),
            listOf(R.id.Tue_1, R.id.Tue_2,R.id.Tue_3,R.id.Tue_4, R.id.Tue_5,R.id.Tue_6,R.id.Tue_7, R.id.Tue_8,R.id.Tue_9),
            listOf(R.id.Wed_1, R.id.Wed_2,R.id.Wed_3,R.id.Wed_4, R.id.Wed_5,R.id.Wed_6,R.id.Wed_7, R.id.Wed_8,R.id.Wed_9),
            listOf(R.id.Thu_1, R.id.Thu_2,R.id.Thu_3,R.id.Thu_4, R.id.Thu_5,R.id.Thu_6,R.id.Thu_7, R.id.Thu_8,R.id.Thu_9),
            listOf(R.id.Fri_1, R.id.Fri_2,R.id.Fri_3,R.id.Fri_4, R.id.Fri_5,R.id.Fri_6,R.id.Fri_7, R.id.Fri_8,R.id.Fri_9)
        )
        for(i in 0 until 5) {
            val courseTmp : MutableList<TextView> = arrayListOf()
            for(j in 0 until 9){
                courseTmp.add(root.findViewById(courseId[i][j]))
            }
            courseText.add(courseTmp)
        }

        val curr = root.findViewById(R.id.full_curriculum) as LinearLayout
        val grade = "1081"
        val url = curriculumViewModel.getUrl(ldapUser,grade)
        val que = Volley.newRequestQueue(curr.context)

        val req = object : JsonObjectRequest(Request.Method.GET, url,null,
            Response.Listener { response ->
                Log.i("ResponseSucces", "Response is: " + response.toString())
                Log.i("ResponseK", "Success")
                curriculumViewModel.set_user(response.getJSONArray(grade))

                val coursequantity = curriculumViewModel.getQuantity()
                Log.i("lengthCQ",coursequantity.toString())
                for(i in 0 until coursequantity){
                    val weekend = curriculumViewModel.getDate(i)-1
                    val start = curriculumViewModel.getStart(i)-1
                    val end = curriculumViewModel.getEnd(i)
                    for(j in start until end){
                        courseText[weekend][j].setText(curriculumViewModel.getName(i))
                    }
                }

            },
            Response.ErrorListener { error ->
                Log.i("ResponseK", "Fuck")
                Log.i("ResponseError", error.toString())
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                val authorization = "Digest " + ldapToken
                headers["Authorization"] = authorization
                return headers
            }
        }

        que.add(req)


        return root
    }
}