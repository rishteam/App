package com.example.fju_course_registration_sys_rish.ui.curriculum

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fju_course_registration_sys_rish.R
import android.view.ViewGroup.LayoutParams.MATCH_PARENT


private val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

class RecyclerCurrAdapter(private val curr : MutableList<UserCourse>, private val context: Context) : RecyclerView.Adapter<RecyclerCurrAdapter.ViewHolder>(){

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
            if( curr[position].startT == curr[position].endT )
                holder.courseCardView.setCardBackgroundColor(0)
        //        holder.itemView.setOnClickListener{

//
//            Toast.makeText(it.context, curr[position].courseName + " is chosen", Toast.LENGTH_SHORT).show()
//            val intent = Intent(it.context, Course_detail::class.java)
//            intent.putExtra("Data", curr[position])
//            it.context.startActivity(intent)
//        }

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
