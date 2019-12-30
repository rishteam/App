package com.example.fju_course_registration_sys_rish.ui.curriculum

import android.graphics.Color
import android.util.Log
import org.json.JSONObject
import java.io.Serializable

class UserCourse : Serializable {
    var course_code: String = ""
    var courseName: String = ""
    var courseDate: Int = 0
    var startT: Int = 0
    var endT: Int = 0
    var color: Int = 0

    init {
        course_code = ""
        courseName = ""
        courseDate = 0
        startT = 0
        endT = 0
        color = 0
    }


    fun parseData(jsonObject: JSONObject){

        Log.i("set_user", jsonObject.toString())
        course_code = if(jsonObject.getString("code").isNotEmpty()) jsonObject.getString("code") else "N/A"
        courseName = jsonObject.getString("subject")
        val T = jsonObject.getJSONArray("time")
        Log.i("find_time",T[0].toString())
        day_ch(T.getJSONObject(0).getString("which_day"))
        Log.i("find_time",courseDate.toString())
        period_Ch(T.getJSONObject(0).getString("period"))
        Log.i("find_time",startT.toString()+" "+endT.toString())

        if( jsonObject.getBoolean("orig") )
            color = Color.rgb(255,255,255)
        if( jsonObject.getBoolean("pick") )
            color = Color.rgb(0xAF,0xFF,0xA6)


    }

    fun emptyCourse(d:Int,t:Int){
//        courseName = "E"
        courseDate = d
        startT = t
        endT = t
        color = Color.argb(64,255, 255, 255)
    }

    fun day_ch(str :String){
        if( str == "Mon" || str == "一" )
            courseDate = 1
        if( str == "Tue" || str == "二" )
            courseDate = 2
        if( str == "Wed" || str == "三" )
            courseDate = 3
        if( str == "Thu" || str == "四" )
            courseDate = 4
        if( str == "Fri" || str == "五" )
            courseDate = 5
    }

    fun period_Ch(str :String){
        val sT = str.substring(0,2)
        val eT = str.substring(3,5)
        Log.i("find_time", sT +" " + eT )
        startT = (sT[1]-'0')
        if( eT[0] == 'E' )
            endT = 9
        endT += (eT[1]-'0')

    }

    fun getStart() : Int {
        return startT
    }
    fun getEnd() : Int {
        return endT
    }
    fun getName() : String {
        return courseName
    }
    fun getDate() : Int {
        return  courseDate
    }
}


