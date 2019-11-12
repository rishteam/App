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
    val course : MutableLiveData<Course> by lazy {

        MutableLiveData<Course>().also {

            Log.i("test", "init")
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is course Fragment"
    }


    fun sendGet(result: LinearLayout) {

        val queue: RequestQueue = Volley.newRequestQueue(result.context)
        val url = "http://vm.rish.com.tw/db/v1/schools"
        val i = Log.i("sendGet", "inSendGet")
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->

                Log.i("sendGet", "am I in?")
                Log.i("sendGet", response.toString())
            }, Response.ErrorListener {

                fun onErrorResponse(error: VolleyError) {

                    Log.e("sendGet", "error")
                }
            })
        queue.add(jsonObjectRequest)

    }

    fun getCourse() : LiveData<Course> {

        Log.i("test", "getCourse")
        return course
    }

    fun getString() : String {

        return "getString"
    }
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

    fun getDataLen(): Int{

        return fake.size
    }
}