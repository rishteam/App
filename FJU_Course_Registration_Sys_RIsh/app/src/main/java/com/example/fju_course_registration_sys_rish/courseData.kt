package com.example.fju_course_registration_sys_rish

import android.app.Application
import com.example.fju_course_registration_sys_rish.ui.course.Course

class CourseData : Application(){

    companion object {

        var courseData: MutableList<Course> = arrayListOf()
    }

    override fun onCreate() {
        super.onCreate()
    }



}