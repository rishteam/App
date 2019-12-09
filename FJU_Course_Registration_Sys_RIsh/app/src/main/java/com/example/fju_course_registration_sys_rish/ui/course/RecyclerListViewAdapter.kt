package com.example.fju_course_registration_sys_rish.ui.course

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fju_course_registration_sys_rish.R
import com.example.fju_course_registration_sys_rish.course_detail

class RecyclerListViewAdapter(private val course : MutableList<Course>, private val context: Context) : RecyclerView.Adapter<RecyclerListViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("adapter", "onCreate")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.courselist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.i("adapter", "onBind")
        holder.textViewID.text = course[position].id.toString()
        holder.textViewUname.text = course[position].schoolName
        holder.textViewProfessor.text = course[position].professor


        holder.itemView.setOnClickListener{

            Toast.makeText(it.context, "ID: " + course[position].id.toString() + " school: " + course[position].schoolName + " is clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(it.context, course_detail::class.java)
            intent.putExtra("Data", course[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        Log.i("adapter", course.size.toString())
        return course.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val textViewID : TextView = view.findViewById(R.id.id)
        val textViewUname : TextView = view.findViewById(R.id.Uname)
        val textViewProfessor: TextView = view.findViewById(R.id.ProfessorName)
    }
}