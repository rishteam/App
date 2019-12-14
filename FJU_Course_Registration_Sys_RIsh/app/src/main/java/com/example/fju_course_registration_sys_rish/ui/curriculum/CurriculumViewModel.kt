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

    var usercourse : MutableList<UserCourse> = arrayListOf()

    fun setUser(response: JSONArray) {

        Log.i("set_user", "in function set_user")
        usercourse.clear()

        for (i in 0 until response.length()){
            val tmp = UserCourse()
            tmp.parseData(response.getJSONObject(i))
            usercourse.add(tmp)
        }

    }

    fun setUserCourse(course : MutableList<UserCourse> ){
        usercourse.clear()
        usercourse = course
    }

    fun getCurUrl(ldapId: String, grade:String):String{
        val str :String = "http://vm.rish.com.tw/db/v1/users/"+ldapId+"/curriculums/"+grade
        return str
    }

    fun getGraUrl(ldapId: String):String{
        val str :String = "http://vm.rish.com.tw/db/v1/users/"+ldapId+"/curriculums"
        return str
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

    fun getUserCourse() :MutableList<UserCourse>{
        return usercourse
    }

}