package com.example.fju_course_registration_sys_rish.ui.curriculum

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.CourseData
import com.example.fju_course_registration_sys_rish.R
import com.example.fju_course_registration_sys_rish.Course_detail
import com.example.fju_course_registration_sys_rish.Home
import com.example.fju_course_registration_sys_rish.UserData.Companion.ldapUser
import com.example.fju_course_registration_sys_rish.ui.course.Course
import com.example.fju_course_registration_sys_rish.ui.search.SearchViewModel

import org.json.JSONArray
import org.json.JSONObject


private val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

class RecyclerCurrAdapter(private val curr : MutableList<UserCourse>, private val context: Context ) : RecyclerView.Adapter<RecyclerCurrAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("adapter", "onCreate")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.i("adapter", "onBind")
//        holder.textViewDepartment.text = curr[position].department

        holder.courseName.text = curr[position].courseName
        holder.courseName.height = 49.dp*(curr[position].endT-curr[position].startT+1) + 10.dp*(curr[position].endT-curr[position].startT)
        holder.courseCardView.setCardBackgroundColor(curr[position].color)

        holder.itemView.setOnClickListener{

            if( curr[position].courseName != "" ){

                val url = "http://vm.rish.com.tw/db/v1/fju_course/courses?course_code="+curr[position].course_code
                val tmpCourse = Course()

                val que = Volley.newRequestQueue(it.context)
                val req = JsonArrayRequest(
                    Request.Method.GET, url, null,
                    Response.Listener<JSONArray> {
                            response ->
                            tmpCourse.parseData(response[0] as JSONObject)
                            Toast.makeText(it.context, tmpCourse.courseName + " is chosen", Toast.LENGTH_SHORT).show()
                            val intent = Intent(it.context, Course_detail::class.java)
                            intent.putExtra("Data", tmpCourse)
                            it.context.startActivity(intent)
                    },
                    Response.ErrorListener { error->

                        Log.e("ResponseError", error.toString())
                    })
                que.add(req)

            }
            else{
//
                var period:String = ""
                if( curr[position].getStart() < 9 ){
                    period = "D"+ curr[position].getStart().toString()
                }
                else{
                    period = "E"+ (curr[position].getStart()-9).toString()
                }

                val url = "http://vm.rish.com.tw/db/v1/fju_course/auto/"+ldapUser+"/"+curr[position].getDate()+"/"+period
                Log.i("ResponseIII",url)
                val que = Volley.newRequestQueue(it.context)
                val req = JsonArrayRequest(Request.Method.GET, url, null,
                    Response.Listener<JSONArray> {
                            response ->
                        Log.i("ResponseIII", response.toString())
                        val searchViewModel = SearchViewModel()
                        searchViewModel.loadData(response)
                        CourseData.courseData = searchViewModel.getList()
                        findNavController(it).navigate(R.id.nav_course)
                    },
                    Response.ErrorListener { error->

                        Toast.makeText(it.context, "Failed to connect the API", Toast.LENGTH_SHORT).show()
                        Log.i("ResponseIII", error.toString())

                    })
                que.add(req)


            }

        }

    }

    override fun getItemCount(): Int {

        Log.i("adapter", curr.size.toString())
        return curr.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val courseName : TextView = view.findViewById(R.id.CourseNameView)
        var courseCardView : CardView = view.findViewById(R.id.CourseCardView)

    }


}
