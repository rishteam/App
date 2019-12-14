package com.example.fju_course_registration_sys_rish.ui.course

import android.util.Log
import org.json.JSONObject
import java.io.Serializable

class Course : Serializable{

    var course_code : String = ""
    var courseName  : String = ""
    var professor   : String = ""
    var department  : String = ""
    var score       : String = ""//學分
    var kind        : String = ""//必修選修
    var times       : String = ""//學期
    var week        : String = ""
    var day         : String = ""
    var period      : String = ""
    var classroom   : String = ""
    var schoolName  : String = ""



    init {

        course_code = ""
        courseName  = ""
        professor   = ""
        department  = ""
        score       = ""//學分
        kind        = ""//必修選修
        times       = ""//學期
        week        = ""
        day         = ""
        period      = ""
        classroom   = ""
        schoolName  = ""

    }

    fun parseData(jsonObject: JSONObject){

        Log.i("Class", jsonObject.toString())
        course_code = jsonObject.getString("course_code")
        courseName  = jsonObject.getString("name")
        professor   = jsonObject.getString("teacher")
        department  = jsonObject.getString("department")
        score       = jsonObject.getString("score")
        kind        = jsonObject.getString("kind")
        times       = jsonObject.getString("times")
        week        = jsonObject.getString("week")
        day         = jsonObject.getString("day")
        period      = jsonObject.getString("period")
        classroom   = jsonObject.getString("classroom")
    }
}

