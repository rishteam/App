package com.example.fju_course_registration_sys_rish.ui.curriculum

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.R
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapToken
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapUser
import com.example.fju_course_registration_sys_rish.UserData.Companion.userCurr
import com.example.fju_course_registration_sys_rish.UserData.Companion.userGra
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_curriculum.*
import kotlinx.android.synthetic.main.fragment_send.view.*
import org.json.JSONArray


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

        val courseText : MutableList<MutableList<TextView>> = arrayListOf()
        val courseId = listOf(
            listOf(R.id.Mon_1, R.id.Mon_2,R.id.Mon_3,R.id.Mon_4, R.id.Mon_5,R.id.Mon_6,R.id.Mon_7, R.id.Mon_8,R.id.Mon_9,R.id.Mon_10,R.id.Mon_11,R.id.Mon_12),
            listOf(R.id.Tue_1, R.id.Tue_2,R.id.Tue_3,R.id.Tue_4, R.id.Tue_5,R.id.Tue_6,R.id.Tue_7, R.id.Tue_8,R.id.Tue_9,R.id.Tue_10,R.id.Tue_11,R.id.Tue_12),
            listOf(R.id.Wed_1, R.id.Wed_2,R.id.Wed_3,R.id.Wed_4, R.id.Wed_5,R.id.Wed_6,R.id.Wed_7, R.id.Wed_8,R.id.Wed_9,R.id.Wed_10,R.id.Wed_11,R.id.Wed_12),
            listOf(R.id.Thu_1, R.id.Thu_2,R.id.Thu_3,R.id.Thu_4, R.id.Thu_5,R.id.Thu_6,R.id.Thu_7, R.id.Thu_8,R.id.Thu_9,R.id.Thu_10,R.id.Thu_11,R.id.Thu_12),
            listOf(R.id.Fri_1, R.id.Fri_2,R.id.Fri_3,R.id.Fri_4, R.id.Fri_5,R.id.Fri_6,R.id.Fri_7, R.id.Fri_8,R.id.Fri_9,R.id.Fri_10,R.id.Fri_11,R.id.Fri_12)
        )
        for(i in 0 until 5) {
            val courseTmp : MutableList<TextView> = arrayListOf()
            for(j in 0 until 9){
                courseTmp.add(root.findViewById(courseId[i][j]))
            }
            courseText.add(courseTmp)
        }

        val curr = root.findViewById(R.id.full_curriculum) as LinearLayout
        val urlGra = curriculumViewModel.getGraUrl(ldapUser)
        if ( userGra.size == 0 )
            getGrade(curr,urlGra)
        for (i in 0 until userGra.size){
            val grade = userGra[i]
            val urlCur = curriculumViewModel.getCurUrl(ldapUser,grade)
            if( userCurr[grade]?.size ?: 0 == 0 )
                getCurr(curr,urlCur,grade)
        }

        Log.i("CheckUserData", userGra.toString())
        for(i in 0 until userGra.size){
            Log.i("CheckUserData", userGra[i])
            val sss = userGra[i]
            for(j in 0 until (userCurr[sss]?.size ?: 0)){
                Log.i("CheckUserData", userCurr[sss]?.get(j)?.getName())
            }
        }

        val gradeSpinner = root.findViewById(R.id.gradeSelect) as Spinner
        val adapter = ArrayAdapter(gradeSpinner.context, android.R.layout.simple_spinner_dropdown_item, userGra)
        gradeSpinner.adapter = adapter
        Log.i("ResponseAdapter", "out $userGra")
        Log.i("ResponseAdapter", "out $adapter")
        Log.i("ResponseAdapter", "out $gradeSpinner")

        gradeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("ResponseCurr","Nothing Select")
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                Log.i("ResponseCurr", "Select: " +userGra[pos])

                for(i in 0 until userGra.size){
                    Log.i("CheckUserDataInSpinner", userGra[i])
                    val sss = userGra[i]
                    for(j in 0 until (userCurr[sss]?.size ?: 0)){
                        Log.i("CheckUserDataInSpinner", userCurr[sss]?.get(j)?.getName())
                    }
                }

                val grade : String = userGra[pos]
                val tmpCurr : MutableList<UserCourse> = arrayListOf()
                tmpCurr.clear()
                if( userCurr[grade] != null ){
                    Log.i("ResponseCurr", userCurr[grade]?.size.toString())
                    for (i in 0 until (userCurr[grade]?.size ?: 0)) {
                        userCurr[grade]?.get(i)?.let { tmpCurr.add(it) }
                        Log.i("ResponseCurr",tmpCurr[i].toString())
                    }
                    Log.i("ResponseCurr","userCurr "+grade+ " success")
                }
                else {
                    Log.i("ResponseCurr", "userCurr " + grade + " is null")
                }

                val coursequantity = tmpCurr.size
                Log.i("ResponseCurr",coursequantity.toString())

                for(i in 0 until 5 )
                    for(j in 0 until 9)
                        courseText[i][j].setText("")

                for(i in 0 until coursequantity){
                    val weekend = tmpCurr[i].getDate()-1
                    val start = tmpCurr[i].getStart()-1
                    val end = tmpCurr[i].getEnd()
                    for(j in start until end){
                        courseText[weekend][j].setText(tmpCurr[i].getName())
                    }
                }


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

    private  fun getCurr(curr: LinearLayout,urlCur :String,grade: String){

        val que = Volley.newRequestQueue(curr.context)
        val reqCur = object : JsonObjectRequest(Request.Method.GET, urlCur,null,
                Response.Listener { response ->
                    Log.i("ResponseK", "Cur Success")
                    Log.i("ResponseSucces", "Response is: " + response.toString())

                    curriculumViewModel.setUser(response.getJSONArray(grade))
                    val tmpUserCourse : MutableList<UserCourse> = curriculumViewModel.getUserCourse()
                    userCurr.put(grade,tmpUserCourse)

                },
                Response.ErrorListener { error ->
                    Log.i("ResponseK", "Cur Fuck")
                    Log.i("ResponseError", error.toString())
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