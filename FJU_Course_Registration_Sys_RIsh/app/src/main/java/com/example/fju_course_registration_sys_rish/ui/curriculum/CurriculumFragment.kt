package com.example.fju_course_registration_sys_rish.ui.curriculum

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.R
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapToken
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapUser
import com.example.fju_course_registration_sys_rish.UserData.Companion.userCurr
import com.example.fju_course_registration_sys_rish.UserData.Companion.userGra
import com.superlht.htloading.view.HTLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CurriculumFragment : Fragment() {

    private lateinit var curriculumViewModel: CurriculumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        curriculumViewModel =
            ViewModelProviders.of(this).get(CurriculumViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_curriculum, container, false)

        val gradeSpinner = root.findViewById(R.id.gradeSelect) as Spinner
        val mon = root.findViewById(R.id.Monday) as LinearLayout
        val tue = root.findViewById(R.id.Tuesday) as LinearLayout
        val wed = root.findViewById(R.id.Wednesday) as LinearLayout
        val thu = root.findViewById(R.id.Thursday) as LinearLayout
        val fri = root.findViewById(R.id.Friday) as LinearLayout
        val week = listOf(
            mon,tue,wed,thu,fri
        )
        val monCourse = root.findViewById(R.id.MondayCourse) as RecyclerView
        val tueCourse = root.findViewById(R.id.TuesdayCourse) as RecyclerView
        val wedCourse = root.findViewById(R.id.WednesdayCourse) as RecyclerView
        val thuCourse = root.findViewById(R.id.ThursdayCourse) as RecyclerView
        val friCourse = root.findViewById(R.id.FridayCourse) as RecyclerView
        val weekCourse = listOf(
            monCourse,tueCourse,wedCourse,thuCourse,friCourse
        )

        val curr = root.findViewById(R.id.full_curriculum) as LinearLayout
        val urlGra = curriculumViewModel.getGraUrl(ldapUser)

        GlobalScope.launch(Dispatchers.Main) {

            HTLoading(curr.context).run {

                setLoadingText("Loading...").show()
                val jobGetGrade = async {

                    Log.i("cortest","Job1")
                    if ( userGra.size == 0 )
                        getGrade(curr, urlGra)
                    Thread.sleep(1500)

                }
                jobGetGrade.await()

                val jobGetCurr = async {
                    Log.i("cortest","Job2")
                    for (i in 0 until userGra.size){
                        val grade = userGra[i]
                        val urlCur = curriculumViewModel.getCurUrl(ldapUser,grade)
                        if( userCurr[grade]?.size ?: 0 == 0 )
                            getCurr(curr,urlCur,grade)
                        Thread.sleep(1500)
                    }

                }
                jobGetCurr.await()

                val jobSetSpin = async {
                    val adapter = ArrayAdapter(gradeSpinner.context, android.R.layout.simple_spinner_dropdown_item, userGra)
                    gradeSpinner.adapter = adapter
                    gradeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            Log.i("ResponseCurr","Nothing Select")
                        }
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {

                            val grade : String = userGra[pos]

                            if( userCurr[grade] != null )
                                curriculumViewModel.getCurrByGlobal(userCurr[grade]!!)

                            val ttt : MutableList<UserCourse> = curriculumViewModel.getCourse(1)
                            for(i in 0 until ttt.size)
                                Log.i("12345678",ttt[i].getName())


                            for(i in 0 until 5) {
                                week[i].removeAllViews()
                                week[i].refreshDrawableState()
                                weekCourse[i].layoutManager = LinearLayoutManager(week[i].context)
                                weekCourse[i].adapter = RecyclerCurrAdapter(curriculumViewModel.getCourse(i+1), week[i].context)
                                week[i].addView(weekCourse[i])
                            }


                        }
                    }
                }
                jobSetSpin.await()

                dismiss()

            }

        }

        return root
    }

    private fun getGrade(curr: LinearLayout,urlGra :String){

        val que = Volley.newRequestQueue(curr.context)
        val reqGra = object : JsonObjectRequest(Request.Method.GET, urlGra,null,
                Response.Listener { response ->
                    Log.i("ResponseGra", "Response is: " + response.toString())
                    Log.i("ResponseK", "Gra Success")
                    val gradeJson = response.getJSONArray("year")

                    userGra.clear()
                    for(i in 0 until gradeJson.length()){
                        userGra.add(gradeJson[i].toString())
                    }


                },
                Response.ErrorListener { error ->
                    Log.i("ResponseK", "Gra Fuck")
                    Log.i("ResponseGra","ERROR:"+error.toString())

                    HTLoading(curr.context).setFailedText("Grade Faild,Please Reopen this Page").showFailed()

                })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                val authorization = "Digest " + ldapToken
                headers["Authorization"] = authorization
                return headers
            }
        }
        que.add(reqGra)

    }

    private fun getCurr(curr: LinearLayout,urlCur :String, grade: String){

        val que = Volley.newRequestQueue(curr.context)
        val reqCur = object : JsonObjectRequest(Request.Method.GET, urlCur,null,
                Response.Listener { response ->
                    Log.i("ResponseK", "Cur Success")
                    Log.i("ResponseSucces", "Response is: " + response.toString())

                    Log.i("KKKKK",grade)

                    val currGetData  = CurriculumViewModel()
                    currGetData.setUser(response.getJSONArray(grade))
                    userCurr.put(grade,currGetData.getUserCourse())


                },
                Response.ErrorListener { error ->
                    Log.i("ResponseK", "Cur Fuck")
                    Log.i("ResponseError", error.toString())

                    getCurr(curr,urlCur,grade)

                })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                val authorization = "Digest " + ldapToken
                headers["Authorization"] = authorization
                return headers
            }
        }
        que.add(reqCur)
    }

}