package com.example.fju_course_registration_sys_rish.ui.curriculum

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray

class CurriculumViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is curriculum Fragment"
    }
    val text: LiveData<String> = _text

    val usercourse : MutableList<UserCourse> = arrayListOf()

    fun set_user(response: JSONArray) {

        Log.i("set_user", "in function set_user")
        usercourse.clear()

        for (i in 0 until response.length()){
            val tmp = UserCourse()
            tmp.parseData(response.getJSONObject(i))
            usercourse.add(tmp)
        }

    }

    fun getQuantity() : Int{
        return usercourse.size
    }
    fun getStart(k:Int) : Int {
        return usercourse[k].startT
    }
    fun getEnd(k:Int) : Int {
        return usercourse[k].endT
    }
    fun getName(k:Int) : String {
        return usercourse[k].courseName
    }
    fun getDate(k:Int) : Int {
        return  usercourse[k].courseDate
    }

}