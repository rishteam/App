package com.example.fju_course_registration_sys_rish.ui.course

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fju_course_registration_sys_rish.R
import com.example.fju_course_registration_sys_rish.Course_detail

class RecyclerListViewAdapter(private val course : MutableList<Course>, private val context: Context) : RecyclerView.Adapter<RecyclerListViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("adapter", "onCreate")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.courselist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.i("adapter", "onBind")
        holder.textViewDepartment.text = course[position].department
        holder.textViewKind.text = course[position].kind
        holder.textViewCourseName.text = course[position].courseName
        holder.textViewDay.text = course[position].day
        holder.textViewPeriod.text = course[position].period
        holder.textViewClassroom.text = course[position].classroom
        holder.textViewProfessor.text = course[position].professor


        holder.itemView.setOnClickListener{

            Toast.makeText(it.context, course[position].courseName + " is chosen", Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context, Course_detail::class.java)
            intent.putExtra("Data", course[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        Log.i("adapter", course.size.toString())
        return course.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val textViewKind : TextView = view.findViewById(R.id.kind)
        val textViewCourseName : TextView = view.findViewById(R.id.CourseName)
        val textViewProfessor: TextView = view.findViewById(R.id.ProfessorName)
        val textViewDepartment : TextView = view.findViewById(R.id.department)
        val textViewDay : TextView = view.findViewById(R.id.day)
        val textViewPeriod : TextView = view.findViewById(R.id.period)
        val textViewClassroom : TextView = view.findViewById(R.id.classroom)

        //        val textViewID : TextView = view.findViewById(R.id.id)
//        val textViewUname : TextView = view.findViewById(R.id.Uname)

    }
}