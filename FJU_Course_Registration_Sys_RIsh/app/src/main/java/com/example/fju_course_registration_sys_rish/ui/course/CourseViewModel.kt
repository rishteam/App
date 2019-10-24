package com.example.fju_course_registration_sys_rish.ui.course

import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fju_course_registration_sys_rish.R
import kotlinx.android.synthetic.main.fragment_course.view.*

class CourseViewModel : ViewModel() {

    val course : MutableLiveData<Course> by lazy {

        MutableLiveData<Course>().also {

            Log.i("test", "init")
            loadCourse()
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is course Fragment"
    }

//    fun display(display_course: MutableList<Course>) {
//
//        Log.i("LOG", "Onclick")
//
////        course.value = display_course[0]
//    }
    fun getCourse() : LiveData<Course> {

        Log.i("test", "getCourse")
        return course
    }

    fun getString() : String {

        return "getString"
    }

    private fun loadCourse(){

        Log.i("test", "load")
        val tmp = Course()
        tmp.courseName = "Pawn"
        tmp.professor  = "Roy"
        tmp.day        = "Thu"
        tmp.duration   = "D5-D7"

        course.postValue(tmp)
    }

}