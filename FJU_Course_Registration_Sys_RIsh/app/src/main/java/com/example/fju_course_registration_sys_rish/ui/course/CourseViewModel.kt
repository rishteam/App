package com.example.fju_course_registration_sys_rish.ui.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CourseViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is course Fragment"
    }
    val text: LiveData<String> = _text
}