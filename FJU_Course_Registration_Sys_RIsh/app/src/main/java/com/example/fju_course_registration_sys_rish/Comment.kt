package com.example.fju_course_registration_sys_rish

import android.util.Log
import org.json.JSONObject

class Comment{

    var commentID      : String = ""
    var stuID           : String = ""
    var classOpen      : String = ""
    var className      : String = ""
    var teacher        : String = ""
    var createDate     : String = ""
    var Quiz           : String = ""
    var MidExam        : String = ""
    var FinalExam      : String = ""
    var PersonalReport : String = ""
    var GroupReport    : String = ""
    var OtherExam      : String = ""
    var OtherWork      : String = ""
    var lvExamAmount   : String = ""
    var lvFun          : String = ""
    var lvLearned      : String = ""
    var lvRequest      : String = ""
    var lvTeachlear    : String = ""
    var lvWork         : String = ""
    var lvRecommend    : String = ""
    var message        : String = ""



    fun loadData(jsonObject : JSONObject) {

        Log.i("comment", jsonObject.toString())

        commentID      = if(jsonObject.getString("commentID").isNotEmpty()) jsonObject.getString("commentID") else "N/A"
        stuID          = if(jsonObject.getString("stuID").isNotEmpty()) jsonObject.getString("stuID") else "N/A"
        classOpen      = if(jsonObject.getString("classOpen").isNotEmpty()) jsonObject.getString("classOpen") else "N/A"
        className      = if(jsonObject.getString("className").isNotEmpty()) jsonObject.getString("className") else "N/A"
        teacher        = if(jsonObject.getString("teacher").isNotEmpty()) jsonObject.getString("teacher") else "N/A"
        createDate     = if(jsonObject.getString("createDate").isNotEmpty()) jsonObject.getString("createDate") else "N/A"
        Quiz           = if(jsonObject.getString("Quiz").isNotEmpty()) jsonObject.getString("Quiz") else "N/A"
        MidExam        = if(jsonObject.getString("MidExam").isNotEmpty()) jsonObject.getString("MidExam") else "N/A"
        FinalExam      = if(jsonObject.getString("FinalExam").isNotEmpty()) jsonObject.getString("FinalExam") else "N/A"
        PersonalReport = if(jsonObject.getString("PersonalReport").isNotEmpty()) jsonObject.getString("PersonalReport") else "N/A"
        GroupReport    = if(jsonObject.getString("GroupReport").isNotEmpty()) jsonObject.getString("GroupReport") else "N/A"
        OtherExam      = if(jsonObject.getString("OtherExam").isNotEmpty()) jsonObject.getString("OtherExam") else "N/A"
        OtherWork      = if(jsonObject.getString("OtherWork").isNotEmpty()) jsonObject.getString("OtherWork") else "N/A"
        lvExamAmount   = if(jsonObject.getString("lvExamAmount").isNotEmpty()) jsonObject.getString("lvExamAmount") else "N/A"
        lvFun          = if(jsonObject.getString("lvFun").isNotEmpty()) jsonObject.getString("lvFun") else "N/A"
        lvLearned      = if(jsonObject.getString("lvLearned").isNotEmpty()) jsonObject.getString("lvLearned") else "N/A"
        lvRequest      = if(jsonObject.getString("lvRequest").isNotEmpty()) jsonObject.getString("lvRequest") else "N/A"
        lvTeachlear    = if(jsonObject.getString("lvTeachlear").isNotEmpty()) jsonObject.getString("lvTeachlear") else "N/A"
        lvWork         = if(jsonObject.getString("lvWork").isNotEmpty()) jsonObject.getString("lvWork") else "N/A"
        lvRecommend    = if(jsonObject.getString("lvRecommend").isNotEmpty()) jsonObject.getString("lvRecommend") else "N/A"
        message        = if(jsonObject.getString("message").isNotEmpty()) jsonObject.getString("message") else "N/A"

    }

    fun emptyLoad(){

        commentID      = ""
        stuID          = ""
        classOpen      = ""
        className      = ""
        teacher        = ""
        createDate     = "1998-12-24 00:00:01"
        Quiz           = ""
        MidExam        = ""
        FinalExam      = ""
        PersonalReport = ""
        GroupReport    = ""
        OtherExam      = ""
        OtherWork      = ""
        lvExamAmount   = ""
        lvFun          = ""
        lvLearned      = ""
        lvRequest      = ""
        lvTeachlear    = ""
        lvWork         = ""
        lvRecommend    = ""
        message        = "No Comment"
    }




}