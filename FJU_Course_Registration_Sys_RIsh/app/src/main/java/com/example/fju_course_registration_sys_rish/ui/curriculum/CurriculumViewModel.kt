package com.example.fju_course_registration_sys_rish.ui.curriculum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurriculumViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is curriculum Fragment"
    }
    val text: LiveData<String> = _text
}