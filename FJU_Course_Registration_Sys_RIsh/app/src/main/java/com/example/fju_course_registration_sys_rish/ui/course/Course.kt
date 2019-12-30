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
    var description : String = ""
    var system      : String = ""
    var link        : String = ""
    var student     : String = ""
    var lang        : String = ""
    var grade       : String = ""


    init {

        course_code = "N/A"
        courseName  = "N/A"
        professor   = "N/A"
        department  = "N/A"
        score       = "N/A"//學分
        kind        = "N/A"//必修選修
        times       = "N/A"//學期
        week        = "N/A"
        day         = "N/A"
        period      = "N/A"
        classroom   = "N/A"
        schoolName  = "N/A"
        description = "N/A"
        system      = "N/A"
        link        = "N/A"
        student     = "N/A"
        lang        = "N/A"
        grade       = "N/A"

    }

    fun parseData(jsonObject: JSONObject){

        Log.i("Class", jsonObject.toString())
        course_code = if(jsonObject.getString("course_code").isNotEmpty()) jsonObject.getString("course_code") else "N/A"
        courseName  = if(jsonObject.getString("name").isNotEmpty()) jsonObject.getString("name") else "N/A"
        professor   = if(jsonObject.getString("teacher").isNotEmpty()) jsonObject.getString("teacher") else "N/A"
        department  = if(jsonObject.getString("department").isNotEmpty()) jsonObject.getString("department") else "N/A"
        score       = if(jsonObject.getString("score").isNotEmpty()) jsonObject.getString("score") else "N/A"
        kind        = if(jsonObject.getString("kind").isNotEmpty()) jsonObject.getString("kind") else "N/A"
        times       = if(jsonObject.getString("times").isNotEmpty()) jsonObject.getString("times") else "N/A"
        week        = if(jsonObject.getString("week").isNotEmpty()) jsonObject.getString("week") else "N/A"
        day         = if(jsonObject.getString("day").isNotEmpty()) jsonObject.getString("day") else "N/A"
        period      = if(jsonObject.getString("period").isNotEmpty()) jsonObject.getString("period") else "N/A"
        classroom   = if(jsonObject.getString("classroom").isNotEmpty()) jsonObject.getString("classroom") else "N/A"
    }

    fun loadDetailData(jsonObject: JSONObject){

        description = if(jsonObject.getString("description").isNotEmpty()) jsonObject.getString("description") else "N/A"
        system      = if(jsonObject.getString("system").isNotEmpty()) jsonObject.getString("system") else "N/A"
        link        = if(jsonObject.getString("link").isNotEmpty()) jsonObject.getString("link") else "N/A"
        student     = if(jsonObject.getString("student").isNotEmpty()) jsonObject.getString("student") else "N/A"
        lang        = if(jsonObject.getString("lang").isNotEmpty()) jsonObject.getString("lang") else "N/A"
        grade       = if(jsonObject.getString("grade").isNotEmpty()) jsonObject.getString("grade") else "N/A"
    }

}

