package com.example.fju_course_registration_sys_rish.ui.course

class Course {

    var courseName : String = ""
    var professor  : String = ""
    var day        : String = ""
    var duration   : String = ""
    var id         : Int = -1
    var testIdx    : Int = 0

    init {
        courseName = ""
        professor  = ""
        day        = ""
        duration   = ""
        id         = -1
    }

    fun loadData(test : Int){

        courseName = "Pawn" + test.toString()
        professor = "Roy" + test.toString()
        day = test.toString()
        duration = "D3-D4"
        testIdx++
    }

}

