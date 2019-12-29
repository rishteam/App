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

        usercourse = usercourse.sortedWith(compareBy(UserCourse::courseDate,UserCourse::startT)).toMutableList()

        for(i in 1 until 6){
            for(j in 1 until 13){
                var flag = false
                for(k in 0 until usercourse.size){
                    if( usercourse[k].getDate() == i && usercourse[k].getStart() <= j && usercourse[k].getEnd() >= j )
                        flag = true
                }
                if( flag )
                    continue
                val tmp = UserCourse()
                tmp.emptyCourse(i,j)
                usercourse.add(tmp)
            }
        }

        usercourse = usercourse.sortedWith(compareBy(UserCourse::courseDate,UserCourse::startT)).toMutableList()

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

    fun getCurrByGlobal(curr:MutableList<UserCourse>){
        usercourse = curr
    }

    fun getCourse(k:Int):MutableList<UserCourse>{

        var tmpK: MutableList<UserCourse> = arrayListOf()
        for( i in 0 until usercourse.size ){
            if( usercourse[i].courseDate == k ){
                tmpK.add(usercourse[i])
            }
        }
        tmpK = tmpK.sortedWith(compareBy(UserCourse::courseDate,UserCourse::startT)).toMutableList()
        return  tmpK
    }

    fun getUserCourse() :MutableList<UserCourse>{
        return usercourse
    }

}