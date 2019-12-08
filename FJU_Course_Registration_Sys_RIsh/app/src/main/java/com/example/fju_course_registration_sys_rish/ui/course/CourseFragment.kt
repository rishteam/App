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
    val url = "http://vm.rish.com.tw/db/v1/schools"
//    private var course         : MutableList<Course> = ArrayList()

    //http://vm.rish.com.tw/db/v1/schools

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
//        val test   = root.findViewById(R.id.test)   as ScrollView

        Log.i("child", "HI")
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

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

//
//                        val listView = ListView(result.context)
//                        listView.id = View.generateViewId()
//                        listView = root.findViewById<ListView>(R.id.list)


                        var UName = ""
                        var ID    = ""

                        listView.layoutManager = LinearLayoutManager(result.context)
                        listView.adapter = RecyclerListViewAdapter(courseViewModel.getList())
//                        val adapter = ArrayAdapter(result.context, android.R.layout.simple_list_item_1, listItems)
//                        listView.adapter = adapter
//
//
                        result.addView(listView)
//                        for(i in 0 until courseViewModel.getDataLen()){
//
//                            val cardView = CardView(result.context)
//                            val cardTextLayout = LinearLayout(result.context)
//
//                            val universityName = TextView(result.context)
//                            val universityID   = TextView(result.context)
//                            val cardViewParams = LinearLayout.LayoutParams(
//                                LinearLayout.LayoutParams.MATCH_PARENT,
//                                400
//                            )
//
//                            cardView.setCardBackgroundColor(Color.parseColor("#000000"))
//                            cardView.setContentPadding(36,36,36,36)
//                            cardView.layoutParams
//                            cardView.layoutParams = cardViewParams
//
//                            val parms = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                            parms.leftMargin = 10
//                            parms.rightMargin = 10
//
//                            cardTextLayout.orientation = LinearLayout.HORIZONTAL
//                            cardTextLayout.gravity = Gravity.CENTER
//
//                            universityName.text = courseViewModel.getUName(i)
//                            universityName.textSize = 24f
//                            universityName.layoutParams = parms
//                            universityID.text = courseViewModel.getID(i).toString()
//                            universityID.textSize = 24f
//                            universityID.layoutParams = parms
//
//                            cardTextLayout.addView(universityID)
//                            cardTextLayout.addView(universityName)
//                            cardView.addView(cardTextLayout)
//                            result.addView(cardView)
//                        }
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


