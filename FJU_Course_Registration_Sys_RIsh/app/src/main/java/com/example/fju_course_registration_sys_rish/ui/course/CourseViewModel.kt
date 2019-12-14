package com.example.fju_course_registration_sys_rish.ui.course

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject

class CourseViewModel : ViewModel() {

    val courseList : MutableList<Course> = arrayListOf()

    fun loadData(response: JSONArray) {

        Log.i("loadData", "in function loadData")
        courseList.clear()
        for(i in 0 until response.length()){

            val course = Course()
            Log.i("loadData", response.getJSONObject(i).toString())
            course.parseData(response.getJSONObject(i))
            courseList.add(course)
        }
    }
    fun getURL(courseName : String, ProfessorName: String) : String{

        var url = "http://vm.rish.com.tw/db/v1/fju_course?"
        if(ProfessorName.length > 0)
            url += "teacher="+ProfessorName+"&"
        if(courseName.length > 0)
            url += "department" + courseName + "&"

        return url

    }


    fun getList() : MutableList<Course>{

        return courseList
    }

    fun getDataLen(): Int{

        Log.i("getDataLen", "return Data len" + " " + courseList.size.toString())
        return courseList.size
    }
}