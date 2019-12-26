package com.example.fju_course_registration_sys_rish.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is recommend Fragment"
    }
    val text: LiveData<String> = _text
}