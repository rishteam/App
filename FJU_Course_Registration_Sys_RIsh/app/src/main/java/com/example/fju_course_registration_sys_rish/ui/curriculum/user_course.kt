package com.example.fju_course_registration_sys_rish.ui.curriculum

import android.util.Log
import org.json.JSONObject

class UserCourse {
    var courseid: Int = 0
    var courseName: String = ""
    var courseDate: Int = 0
    var startT: Int = 0
    var endT: Int = 0

    init {
        courseid = 0
        courseName = ""
        courseDate = 0
        startT = 0
        endT = 0
    }

    fun parseData(jsonObject: JSONObject){

        Log.i("set_user", jsonObject.toString())
        courseName = jsonObject.getString("subject")
        val T = jsonObject.getJSONArray("time")
        Log.i("find_time",T[0].toString())
        day_ch(T.getJSONObject(0).getString("which_day"))
        Log.i("find_time",courseDate.toString())
        period_Ch(T.getJSONObject(0).getString("period"))
        Log.i("find_time",startT.toString()+" "+endT.toString())

    }

    fun day_ch(str :String){
        if( str == "一")
            courseDate = 1
        if( str == "二")
            courseDate = 2
        if( str == "三")
            courseDate = 3
        if( str == "四")
            courseDate = 4
        if( str == "五")
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

}


