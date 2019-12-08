package com.example.fju_course_registration_sys_rish.ui.course

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject

class CourseViewModel : ViewModel() {

    val fakeData : MutableList<Course> = mutableListOf()
    val fake     : MutableList<Course> = mutableListOf()
    val url = "http://vm.rish.com.tw/db/v1/schools"
    val json : JSONObject = JSONObject()
    val courseList : MutableList<Course> = arrayListOf()
    var loadFinish : Boolean = false
    var loading    : Boolean = false


    fun loadData(response: JSONArray) {

        Log.i("loadData", "in function loadData")
        courseList.clear()
        for(i in 0 until response.length()){

            val course = Course()
            Log.i("loadData", response.getJSONObject(i).toString())
            course.parseData(response.getJSONObject(i))
            courseList.add(course)
            Log.i("1234456", courseList[i].schoolName + i.toString())
        }
        Log.i("setLoad", "toSet")
//                    setLoadFinish()
        Log.i("len5", courseList.size.toString())
    }

    fun store(response: JSONArray){

        val course = Course()
        for(i in 0 until response.length()){

//                    Log.i("loadData", response.getJSONObject(i).toString())
            course.parseData(response.getJSONObject(i))
            courseList.add(course)
            Log.i("opo", courseList[i].schoolName + i.toString())
        }
        Log.i("len", courseList[0].schoolName)
    }

//    fun getCourse() : LiveData<Course> {
//
//        Log.i("test", "getCourse")
//        return course
//    }


    fun getUName(i: Int): String {

        return courseList[i].schoolName
    }

    fun getID(i: Int): Int {

        return courseList[i].id
    }

    fun getCourseName(i: Int): String {

        Log.i("load" , "CourseName")

        return fake[i].courseName

    }

    fun getProfessorName(i: Int): String{

        return fake[i].professor

    }

    fun getDuration(i: Int): String{

        return fake[i].duration

    }

    fun getDay(i: Int): String{

        return fake[i].day
    }

    fun isLoading(): Boolean {

        return loading
    }

    fun getList() : MutableList<Course>{

        return courseList
    }

    fun getDataLen(): Int{

        Log.i("getDataLen", "return Data len" + " " + courseList.size.toString())
        return courseList.size
    }
}