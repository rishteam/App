package com.example.fju_course_registration_sys_rish.ui.course

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fju_course_registration_sys_rish.R

class RecyclerListViewAdapter(private val course : MutableList<Course>) : RecyclerView.Adapter<RecyclerListViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("adapter", "onCreate")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.courselist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.i("adapter", "onBind")
        holder.textViewID.text = course[position].id.toString()
        holder.textViewUname.text = course[position].schoolName
    }

    override fun getItemCount(): Int {

        Log.i("adapter", course.size.toString())
        return course.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val textViewID : TextView = view.findViewById(R.id.id)
        val textViewUname : TextView = view.findViewById(R.id.Uname)
    }
}