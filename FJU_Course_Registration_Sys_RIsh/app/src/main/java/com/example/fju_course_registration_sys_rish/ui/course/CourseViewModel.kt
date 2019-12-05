package com.example.fju_course_registration_sys_rish.ui.course

import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.*
import com.example.fju_course_registration_sys_rish.R
import kotlinx.android.synthetic.main.fragment_course.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class CourseViewModel : ViewModel() {

    val fakeData : MutableList<Course> = mutableListOf()
    val fake     : MutableList<Course> = mutableListOf()
    val url = "http://vm.rish.com.tw/db/v1/schools"
    val json : JSONObject = JSONObject()
    val courseList : MutableList<Course> = arrayListOf()
    var loadFinish : Boolean = false
    var loading    : Boolean = false



//    val courseList : MutableLiveData<Course> by lazy {
//
//        MutableLiveData<Course>().also {
//
//            Log.i("test", "init")
//        }
//    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is course Fragment"
    }


    fun loadData(result: LinearLayout) {

        Log.i("loadData", "in function loadData")

        val que = Volley.newRequestQueue(result.context)
        val req = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener<JSONArray> {
                    response ->

                Log.i("oaa", response.length().toString())
                Log.i("oao", response.toString())
                for(i in 0 until response.length()){

                    val course = Course()
                    Log.i("loadData", response.getJSONObject(i).toString())
                    course.parseData(response.getJSONObject(i))
                    courseList.add(course)
                    Log.i("1234456", courseList[i].schoolName + i.toString())
                }
                Log.i("setLoad", "toSet")
//                    setLoadFinish()
                Log.i("len5", courseList.size.toString())

            },
            Response.ErrorListener { error->

                Log.i("eeeeeeee", "fuck")
                Log.e("stupid", error.toString())
            })
        que.add(req)
        loading = false

//        store(aaa)
    }

    fun store(response: JSONArray){

        val course = Course()
        for(i in 0 until response.length()){

//                    Log.i("loadData", response.getJSONObject(i).toString())
            course.parseData(response.getJSONObject(i))
            courseList.add(course)
            Log.i("opo", courseList[i].schoolName + i.toString())
        }
        Log.i("len", courseList[0].schoolName)
    }

//    fun getCourse() : LiveData<Course> {
//
//        Log.i("test", "getCourse")
//        return course
//    }

    fun fakeLoad(){

        for(i in 0 until 10){

            val tmp = Course()
            tmp.loadData(i)//for test
            fakeData.add(tmp)
        }
        Log.i("fakeLoad", "Load success")
    }

    fun loadCourse(CName: String, PName: String, D_fil: String, Dur: String){

        Log.i("loadCourse", "load")

        for(i in 0 until 10){

//            val tmp = Course()
//            tmp.loadData(i)//for test
//            fakeData.add(tmp)


                Log.i("filter", "success")
                fake.add(fakeData[i])


        }

//        course.postValue(tmp)
    }

    fun getUName(i: Int): String {

        return courseList[i].schoolName
    }

    fun getID(i: Int): Int {

        return courseList[i].id
    }

    fun getCourseName(i: Int): String {

        Log.i("load" , "CourseName")

        return fake[i].courseName

    }

    fun getProfessorName(i: Int): String{

        return fake[i].professor

    }

    fun getDuration(i: Int): String{

        return fake[i].duration

    }

    fun getDay(i: Int): String{

        return fake[i].day
    }

    fun isLoading(): Boolean {

        return loading
    }

    fun getDataLen(): Int{

        Log.i("getDataLen", "return Data len" + " " + courseList.size.toString())
        return courseList.size
    }
}