package com.example.fju_course_registration_sys_rish

import android.app.Application


class UserData : Application() {
    companion object {
        var ldapUser = ""
        var ldapToken = ""
    }

    override fun onCreate() {
        super.onCreate()
    }
}






