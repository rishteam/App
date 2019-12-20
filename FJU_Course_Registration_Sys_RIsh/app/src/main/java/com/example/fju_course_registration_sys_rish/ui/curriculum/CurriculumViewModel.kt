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

    fun setUser(response: JSONArray) {

        Log.i("set_user", "in function set_user")
        usercourse.clear()

        for (i in 0 until response.length()){
            val tmp = UserCourse()
            tmp.parseData(response.getJSONObject(i))
            usercourse.add(tmp)
        }

    }

    fun addCourse(course : UserCourse ){
        usercourse.add(course)
    }

    fun getCurUrl(ldapId: String, grade:String):String{
        val str :String = "http://vm.rish.com.tw/db/v1/users/"+ldapId+"/curriculums/"+grade
        return str
    }

    fun getGraUrl(ldapId: String):String{
        val str :String = "http://vm.rish.com.tw/db/v1/users/"+ldapId+"/curriculums"
        return str
    }

    fun getUserCourse() :MutableList<UserCourse>{
        return usercourse
    }

}