package com.example.fju_course_registration_sys_rish.ui.course

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fju_course_registration_sys_rish.R
import android.util.Log
import android.view.Gravity
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_course.*
import org.json.JSONArray

class CourseFragment : Fragment() {

    private lateinit var courseViewModel: CourseViewModel
    var url = ""
    val search : MutableMap<String, String> = mutableMapOf(
        "course_code" to "",
        "name"        to "",
        "teacher"     to "",
        "department"  to "",
        "score"       to "",
        "kind"        to "",
        "times"       to "",
        "day"         to "",
        "week"        to "",
        "period"      to "",
        "classroom"   to ""
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        courseViewModel =
            ViewModelProviders.of(this).get(CourseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_course, container, false)

        val button = root.findViewById(R.id.filter) as MaterialButton
        val result = root.findViewById(R.id.result) as LinearLayout
        val listView = root.findViewById(R.id.listView) as RecyclerView
        Log.i("child", "HI")
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                search["name"] = Course_name.text.toString()
                search["teacher"] = Professor.text.toString()

//                val inputCourse = Course_name.text.toString()
//                val inputProfessor = Professor.text.toString()
                /*
                search["course_code"]
                search["department"]
                search["score"]
                search["kind"]
                search["times"]
                search["day"]
                search["week"]
                search["period"]
                search["classroom"]
                  */

                url = courseViewModel.getURL(search)
                Log.i("2345677", url)

                val que = Volley.newRequestQueue(result.context)
                val req = JsonArrayRequest(Request.Method.GET, url, null,
                    Response.Listener<JSONArray> {
                            response ->

                        result.removeAllViews()
                        result.refreshDrawableState()

                        Log.i("response", response.toString())

                        courseViewModel.loadData(response)
                        Log.i("LoadData", courseViewModel.getList().toString())
                        Log.i("uuu", courseViewModel.getDataLen().toString())

                        listView.layoutManager = LinearLayoutManager(result.context)
                        listView.adapter = RecyclerListViewAdapter(courseViewModel.getList(), result.context)

                        result.addView(listView)
                    },
                    Response.ErrorListener { error->

                        Log.e("ResponseError", error.toString())
                    })
                que.add(req)
            }
        })
        return root
    }


}


