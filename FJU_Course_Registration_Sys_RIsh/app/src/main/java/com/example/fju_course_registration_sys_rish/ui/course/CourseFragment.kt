package com.example.fju_course_registration_sys_rish.ui.course

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fju_course_registration_sys_rish.R
import kotlinx.android.synthetic.main.fragment_course.*
import android.util.Log
import android.view.Gravity
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.marginLeft
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlin.math.log
import kotlin.reflect.typeOf


class CourseFragment : Fragment() {

    private lateinit var courseViewModel: CourseViewModel
//    private var course         : MutableList<Course> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        courseViewModel =
            ViewModelProviders.of(this).get(CourseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_course, container, false)

        courseViewModel.fakeLoad()
        var flag: Boolean = true
        val button = root.findViewById(R.id.filter) as MaterialButton
        val result = root.findViewById(R.id.result) as LinearLayout
//
        Log.i("child", "HI")
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                Log.i("test", "click")
//                Log.i("child", result.childCount.toString())

                val CName = Course_name.text.toString()
                val PName = Professor.text.toString()
                val D_fil = Day.text.toString()
                val Dur  = Duration.text.toString()

                Log.i("CName", CName)
                Log.i("PName", PName)
                Log.i("D_fil", D_fil)
                Log.i("Dur", Dur)

                courseViewModel.loadCourse(CName, PName, D_fil, Dur)

                for(i in 0 until courseViewModel.getDataLen()){

                    val cardView = CardView(result.context)
                    val cardTextLayout = LinearLayout(result.context)
                    val duration = TextView(result.context)
                    val quote = TextView(result.context)
                    val professorName = TextView(result.context)
                    val day = TextView(result.context)
                    val cardViewParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        400
                    )


                    cardView.setCardBackgroundColor(Color.parseColor("#000000"))
                    cardView.setContentPadding(36,36,36,36)
                    cardView.layoutParams
                    cardView.layoutParams = cardViewParams


//                val cardLinearLayout = LinearLayout(result.context)
//                cardLinearLayout.orientation = LinearLayout.VERTICAL


                    val parms = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    parms.leftMargin = 10
                    parms.rightMargin = 10



                    cardTextLayout.orientation = LinearLayout.HORIZONTAL
                    cardTextLayout.gravity = Gravity.CENTER


                    duration.text = courseViewModel.getDuration(i)
                    duration.textSize = 24f
                    duration.layoutParams = parms



                    quote.text = courseViewModel.getCourseName(i)
                    quote.textSize = 24f
                    quote.layoutParams = parms


                    professorName.text = courseViewModel.getProfessorName(i)
                    professorName.textSize = 24f
                    professorName.layoutParams = parms


                    day.text = courseViewModel.getDay(i)
                    day.textSize = 24f
                    day.layoutParams = parms


                    cardView.setOnClickListener{

                        Toast.makeText(result.context, "Card clicked.", Toast.LENGTH_SHORT).show()
                    }


                    cardTextLayout.addView(duration)
                    cardTextLayout.addView(quote)
                    cardTextLayout.addView(professorName)
                    cardTextLayout.addView(day)
                    cardView.addView(cardTextLayout)
                    result.addView(cardView)
                }
                flag = false


            }

        })
        return root
    }


}


