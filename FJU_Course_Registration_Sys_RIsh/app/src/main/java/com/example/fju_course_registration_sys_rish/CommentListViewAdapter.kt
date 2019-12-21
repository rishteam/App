package com.example.fju_course_registration_sys_rish

import android.animation.ObjectAnimator
import android.text.TextUtils
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class CommentListViewAdapter(private val comment : MutableList<Comment>) : RecyclerView.Adapter<CommentListViewAdapter.ViewHolder>() {

    val DEFAULT_MAXLINE = 5
    var MAXLINE         = 1000

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.i("adapter", "onBind")
        holder.studentId.text = comment[position].stuID
        holder.time.text = getTimeAgo(comment[position].createDate)
        holder.comment.text = comment[position].message
//
//        holder.studentId.text = "123"
//        holder.time.text = getTimeAgo("2019-12-18 09:22:23")
//        holder.comment.text = "huhfufheuhfu"

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
                MAXLINE = holder.comment.text.length/10 + 1
                val animation = ObjectAnimator.ofInt(holder.comment, "maxLines", MAXLINE)
                animation.setDuration(200).start()
            }

//            val animation = ObjectAnimator.ofInt(holder.comment, "maxLines", 25)
//            animation.setDuration(20000000000).start()
            Log.i("animate2", holder.comment.maxLines.toString())
        }

    }

    override fun getItemCount(): Int {

        return comment.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val studentId: TextView = view.findViewById(R.id.studentID)
        val time: TextView = view.findViewById(R.id.time)
        val comment: TextView = view.findViewById(R.id.comment)
    }

    fun getTimeAgo(time: String): String {

        val simpleDataFormat = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN)
        simpleDataFormat.timeZone = TimeZone.getTimeZone(time)
        val createTime = simpleDataFormat.parse(time).time
        val currentTime = System.currentTimeMillis()
        Log.i("TimeAgo", currentTime.toString())
        Log.i("TimeAgo", DateUtils.getRelativeTimeSpanString(createTime, currentTime, DateUtils.MINUTE_IN_MILLIS).toString())
        return DateUtils.getRelativeTimeSpanString(createTime, currentTime, DateUtils.SECOND_IN_MILLIS).toString()
    }

}
