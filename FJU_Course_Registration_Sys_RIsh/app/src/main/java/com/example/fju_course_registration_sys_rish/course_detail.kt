package com.example.fju_course_registration_sys_rish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fju_course_registration_sys_rish.ui.course.Course
import kotlinx.android.synthetic.main.activity_course_detail.*

class course_detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val course = intent.getSerializableExtra("Data") as? Course

        val id = detail_id
        val Uname = detail_Uname

        id.text = course!!.schoolName
        Uname.text = course.id.toString()


    }
}
