package com.example.fju_course_registration_sys_rish.ui.course

import android.util.Log
import org.json.JSONObject

class Course {

    var schoolName : String = ""
    var courseName : String = ""
    var professor  : String = ""
    var day        : String = ""
    var duration   : String = ""
    var id         : Int = -1
    var testIdx    : Int = 0

    init {

        schoolName = ""
        courseName = ""
        professor  = ""
        day        = ""
        duration   = ""
        id         = -1
    }

    fun parseData(jsonObject: JSONObject){

        Log.i("Class", jsonObject.toString())
        schoolName = jsonObject.getString("name")
        id         = jsonObject.getInt("id")
    }

    fun loadData(test : Int){

        courseName = "Pawn" + test.toString()
        professor = "Roy" + test.toString()
        day = test.toString()
        duration = "D3-D4"
        testIdx++
    }

}

