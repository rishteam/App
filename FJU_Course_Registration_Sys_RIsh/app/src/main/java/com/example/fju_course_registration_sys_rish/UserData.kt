package com.example.fju_course_registration_sys_rish

import android.app.Application
import com.example.fju_course_registration_sys_rish.ui.curriculum.UserCourse


class UserData : Application() {
    companion object {
        var ldapUser = "406262319"
        var ldapToken = "c3330b6478b7abb4915da83ee75f8195"
        var userGra :MutableList<String> = arrayListOf()
        val userCurr: MutableMap < String, MutableList<UserCourse> > = mutableMapOf()
    }

    override fun onCreate() {
        super.onCreate()
    }
}






