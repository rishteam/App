package com.example.fju_course_registration_sys_rish.ui.curriculum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurriculumViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is curriculum Fragment"
    }
    val text: LiveData<String> = _text

    val usercourse : MutableList<UserCourse> = arrayListOf()

    fun set_user() {
        val tmp = UserCourse()
        tmp.courseName = "Fuck"
        tmp.courseDate = 2
        tmp.startT = 4
        tmp.endT = 6
        usercourse.add(tmp)
        val tmp_2 = UserCourse()
        tmp_2.courseName = "Shit"
        tmp_2.courseDate = 3
        tmp_2.startT = 2
        tmp_2.endT = 3
        usercourse.add(tmp_2)
        val tmp_3 = UserCourse()
        tmp_3.courseName = "Test"
        tmp_3.courseDate = 4
        tmp_3.startT = 4
        tmp_3.endT = 5
        usercourse.add(tmp_3)
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