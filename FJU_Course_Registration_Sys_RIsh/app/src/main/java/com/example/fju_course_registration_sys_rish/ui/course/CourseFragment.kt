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
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import android.widget.Toast


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
//        val textView: TextView = root.findViewById(R.id.text_course)
//        courseViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

        val button = root.findViewById(R.id.filter) as MaterialButton
        val result = root.findViewById(R.id.result) as LinearLayout
        var t = 1
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                Log.i("test", "click")

                val cardView = CardView(result.context)
                cardView.setCardBackgroundColor(Color.parseColor("#000000"))
                cardView.setContentPadding(36,36,36,36)

                val cardLinearLayout = LinearLayout(result.context)
                cardLinearLayout.orientation = LinearLayout.VERTICAL


                val quote = TextView(result.context)
                quote.text = "Royyyyyy"
                quote.textSize = 24f

                cardView.setOnClickListener{

                    Toast.makeText(result.context, "Card clicked.", Toast.LENGTH_SHORT).show()
                }


                cardLinearLayout.addView(quote)
                cardView.addView(cardLinearLayout)
                result.addView(cardView)



//                val testButton = MaterialButton(result.context)
//                testButton.setOnClickListener(this)
//                testButton.setText("test$t")
//                t++
//                Log.i("test", "generate")
//                result.addView(testButton)
            }
        })

        return root
    }


}


