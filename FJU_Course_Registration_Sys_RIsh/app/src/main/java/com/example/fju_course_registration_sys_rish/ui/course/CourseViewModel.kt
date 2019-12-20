package com.example.fju_course_registration_sys_rish.ui.course

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject

class CourseViewModel : ViewModel() {

    val courseList : MutableList<Course> = arrayListOf()
    val parameterList : List<String> = listOf(
        "course_code",
        "name",
        "teacher",
        "department",
        "score",
        "kind",
        "times",
        "day",
        "week",
        "period",
        "classroom"
    )


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
    fun getURL(search: MutableMap<String, String>) : String{

        var url = "http://vm.rish.com.tw/db/v1/fju_course/courses?"

        for(i in parameterList){

            if(search[i]!!.isNotEmpty())
                url += i + "=" + search[i] + "&"
        }
//        if(ProfessorName.length > 0)
//            url += "teacher="+ProfessorName+"&"
//        if(courseName.length > 0)
//            url += "department" + courseName + "&"
        Log.i("1234566", url)
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