package com.example.fju_course_registration_sys_rish.ui.course

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
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
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.schedule
import kotlin.math.log
import kotlin.reflect.typeOf


class CourseFragment : Fragment() {

    private lateinit var courseViewModel: CourseViewModel
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

        Log.i("child", "HI")
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {



                courseViewModel.loadData(result)


                Log.i("test", "click")

                var UName = ""
                var ID    = ""

                for(i in 0 until courseViewModel.getDataLen()){

                    Log.i("uuu", courseViewModel.getUName(i))
                    val cardView = CardView(result.context)
                    val cardTextLayout = LinearLayout(result.context)

                    val universityName = TextView(result.context)
                    val universityID   = TextView(result.context)
                    val cardViewParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        400
                    )


                    cardView.setCardBackgroundColor(Color.parseColor("#000000"))
                    cardView.setContentPadding(36,36,36,36)
                    cardView.layoutParams
                    cardView.layoutParams = cardViewParams

                    val parms = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    parms.leftMargin = 10
                    parms.rightMargin = 10

                    cardTextLayout.orientation = LinearLayout.HORIZONTAL
                    cardTextLayout.gravity = Gravity.CENTER

                    universityName.text = courseViewModel.getUName(i)
                    universityName.textSize = 24f
                    universityName.layoutParams = parms
                    universityID.text = courseViewModel.getID(i).toString()
                    universityID.textSize = 24f
                    universityID.layoutParams = parms

                    cardView.setOnClickListener{

                        Toast.makeText(result.context, "Card clicked.", Toast.LENGTH_SHORT).show()
                    }
                    cardTextLayout.addView(universityID)
                    cardTextLayout.addView(universityName)
                    cardView.addView(cardTextLayout)
                    result.addView(cardView)
                }
            }

        })
        return root
    }


}


