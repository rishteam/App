package com.example.fju_course_registration_sys_rish

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.ui.course.Course
import org.json.JSONObject
import android.animation.ObjectAnimator
import android.widget.Toast
import com.superlht.htloading.view.HTLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class InfoFragment(private val course: Course) : Fragment(){

    val DEFAULT_MAXLINES = 5
    var MAXLINES = 1000
    var url="http://vm.rish.com.tw/db/v1/fju_course/courses/details?cid="

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater!!.inflate(R.layout.fragment_info, container, false)

        val outlineCardView = root.findViewById(R.id.outlineCardView) as CardView
        val info = root.findViewById(R.id.infoLayout) as LinearLayout
        val category = root.findViewById(R.id.category) as TextView
        val CName = root.findViewById(R.id.CName) as TextView
        val DName = root.findViewById(R.id.DName) as TextView
        val Grade = root.findViewById(R.id.grade) as TextView
        val Credit = root.findViewById(R.id.credit) as TextView
        val TName = root.findViewById(R.id.TName) as TextView
        val Exp = root.findViewById(R.id.Expertise) as TextView
        val Day = root.findViewById(R.id.weekDay) as TextView
        val Period = root.findViewById(R.id.coursePeriod) as TextView
        val Outline = root.findViewById(R.id.courseOutline) as TextView
        val more_less = root.findViewById(R.id.more_less) as TextView

        url += course.course_code

        GlobalScope.launch(Dispatchers.Main) {

            HTLoading(info.context).run {

                setLoadingText("Loading...").show()

                val job = async {
                    Log.i("seeURL", url)
                    val que = Volley.newRequestQueue(info.context)
                    val req = JsonObjectRequest(
                        Request.Method.GET, url, null,
                        Response.Listener<JSONObject> { response ->

                            DName.text = course.department
                            Grade.text = "N/A"
                            course.loadDetailData(response)

                            category.text = course.kind
                            CName.text = course.courseName
                            Credit.text = course.score
                            TName.text = course.professor
                            Exp.text = "N/A"
                            Day.text = course.day
                            Period.text = course.period
                            Outline.text = course.description

                            MAXLINES = Outline.text.length / 17 + 1
                        },
                        Response.ErrorListener { error ->

                            Log.e("ResponseError", error.toString())
                        })
                    que.add(req)
                    Thread.sleep(2000)
                }
                job.await()

                dismiss()
            }

        }


        var isExpanded : Boolean = false
        outlineCardView.setOnClickListener {

            if(isExpanded){

                Log.i("outLineClicked", "expand")

                val animation = ObjectAnimator.ofInt(Outline, "maxLines", DEFAULT_MAXLINES)
                animation.setDuration(200).start()
                isExpanded = false
                more_less.setText(getString(R.string.string, "more"))
            }
            else{

                Log.i("outLineClicked", "collapse")
                val animation = ObjectAnimator.ofInt(Outline, "maxLines", MAXLINES)
                animation.setDuration(200).start()
                isExpanded = true
                more_less.setText(getString(R.string.string, "less"))
            }
        }

        return root
    }


}