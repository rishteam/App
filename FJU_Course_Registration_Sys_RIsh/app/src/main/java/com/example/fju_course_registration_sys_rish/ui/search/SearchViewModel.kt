package com.example.fju_course_registration_sys_rish.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fju_course_registration_sys_rish.ui.course.Course
import org.json.JSONArray

class SearchViewModel : ViewModel() {

    val courseList : MutableList<Course> = arrayListOf()
    val parameterList : List<String> = listOf(
        "course_code",
        "name",
        "teacher",
        "department",
        "period"
    )

    val kindList : List<String> = listOf(

        "major",
        "elective",
        "general",
        "minor"
    )
    val kindMap : Map<String, String> = mapOf(

        "major" to "必",
        "elective" to "選",
        "general" to "通",
        "minor" to "輔"
    )

    val dayList : List<String> = listOf(

        "mon",
        "tue",
        "wed",
        "thu",
        "fri",
        "sat",
        "sun"
    )

    val dayMap : Map<String, String> = mapOf(

        "mon" to "1",
        "tue" to "2",
        "wed" to "3",
        "thu" to "4",
        "fri" to "5",
        "sat" to "6",
        "sun" to "7"
    )



    private val _text = MutableLiveData<String>().apply {
        value = "This is search Fragment"
    }
    val text: LiveData<String> = _text

    fun getURL(search: MutableMap<String, String>, day: MutableMap<String, Boolean>, overlap: Boolean, kind: MutableMap<String, Boolean>) : String{

        Log.i("seeKind", kind["major"].toString())

        var url = "http://vm.rish.com.tw/db/v1/fju_course/courses?"

        for(i in parameterList){

            if(search[i]!!.isNotEmpty())
                url += i + "=" + search[i] + "&"
        }

        var d = ""
        for(i in dayList){

            if(day[i]!!){

                d += dayMap[i]
            }
        }

        if(d.isNotEmpty()){

            url += "day=" + d + "&"
        }

        if(overlap){

            url += "include=" + overlap.toString() + "&"
        }

        var kindtext = ""
        for(i in kindList){

            if(kind[i]!!){

                kindtext += kindMap[i]
            }
        }
        if(kindtext.isNotEmpty()){

            url += "kind=" + kindtext
        }

        return url

    }


    fun loadData(response: JSONArray) {

        Log.i("loadData", "in function loadData")
        courseList.clear()
        for(i in 0 until response.length()){

            val course = Course()
            Log.i("loadData", response.getJSONObject(i).toString())
            course.parseData(response.getJSONObject(i))
            courseList.add(course)
        }
    }
    fun getList() : MutableList<Course>{

        return courseList
    }
}