package com.example.fju_course_registration_sys_rish

import android.app.Application
import android.util.Log
import com.example.fju_course_registration_sys_rish.ui.curriculum.UserCourse
import org.json.JSONArray


class UserData : Application() {
    companion object {
        var ldapUser = ""
        var ldapToken = ""
        var userGra :MutableList<String> = arrayListOf()
        val userCurr: MutableMap < String, MutableList<UserCourse> > = mutableMapOf()
    }

    override fun onCreate() {
        super.onCreate()
    }
}






