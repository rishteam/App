package com.example.fju_course_registration_sys_rish

import android.animation.ObjectAnimator
import android.graphics.Paint
import android.graphics.Rect
import android.provider.Settings.Global.getInt
import android.text.TextUtils
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fju_course_registration_sys_rish.R
import java.lang.Integer.getInteger



class CommentListViewAdapter() : RecyclerView.Adapter<CommentListViewAdapter.ViewHolder>() {

    val DEFAULT_MAXLINE = 5
    var MAXLINE         = 1000

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.i("adapter", "onBind")
        holder.studentId.text = "406262084"
        holder.time.text = "30年前"
        holder.comment.text = "中文大概應該也許我猜一行可以有十七個文字然後我要不斷的測試測試測試測試測試測試中文大概應該也許我猜一行可以有十七個文字然後我要不斷的測試測試測試測試測試測試中文大概應該也許我猜一行可以有十七個文字然後我要不斷的測試測試測試測試測試測試中文大概應該也許我猜一行可以有十七個文字然後我要不斷的測試測試測試測試測試測試"

        MAXLINE = holder.comment.text.length/17 + 1
        holder.comment.setEllipsize(TextUtils.TruncateAt.END)
        holder.comment.setMaxLines(DEFAULT_MAXLINE)
        Log.i("animate1111", MAXLINE.toString())
        var isExpand : Boolean = false
        var isClicked = false
        holder.itemView.setOnClickListener{

            if(isExpand){

//                holder.comment.setMaxLines(DEFAULT_MAXLINE)
                holder.comment.setEllipsize(TextUtils.TruncateAt.END)
                isExpand = false
                val animation = ObjectAnimator.ofInt(holder.comment, "maxLines", DEFAULT_MAXLINE)
                animation.setDuration(200).start()
            }
            else{

//                holder.comment.setMaxLines(25)
                holder.comment.setEllipsize(null)
                isExpand = true
                val animation = ObjectAnimator.ofInt(holder.comment, "maxLines", MAXLINE)
                animation.setDuration(200).start()
            }

//            val animation = ObjectAnimator.ofInt(holder.comment, "maxLines", 25)
//            animation.setDuration(20000000000).start()
            Log.i("animate2", holder.comment.maxLines.toString())
        }

    }

    override fun getItemCount(): Int {

        return 10
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val studentId: TextView = view.findViewById(R.id.studentID)
        val time: TextView = view.findViewById(R.id.time)
        val comment: TextView = view.findViewById(R.id.comment)
    }

}
