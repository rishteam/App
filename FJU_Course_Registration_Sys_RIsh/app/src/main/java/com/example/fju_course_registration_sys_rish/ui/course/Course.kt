package com.example.fju_course_registration_sys_rish.ui.course

import android.util.Log
import org.json.JSONObject
import java.io.Serializable

class Course : Serializable{

    var schoolName : String = ""
    var courseName : String = ""
    var professor  : String = ""
    var day        : String = ""
    var duration   : String = ""
    var id         : String = ""
    var testIdx    : Int = 0

    init {

        schoolName = ""
        courseName = ""
        professor  = ""
        day        = ""
        duration   = ""
        id         = ""
    }

    fun parseData(jsonObject: JSONObject){

        Log.i("Class", jsonObject.toString())
        schoolName = jsonObject.getString("name")
        id         = jsonObject.getString("course_code")
        professor  = jsonObject.getString("teacher")
    }
}

