package com.example.fju_course_registration_sys_rish

import android.animation.ObjectAnimator
import android.os.SystemClock
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
        Log.i("TimeAgo", comment[position].createDate)

        if(comment[position].stuID == "000000000"){

            holder.nickname.text = "來自" + comment[position].source
        }
        else{

            holder.nickname.text = comment[position].nickName
            holder.studentId.text = comment[position].stuID
        }

        holder.time.text = getTimeAgo(comment[position].createDate)
        holder.comment.text = comment[position].message
        holder.comment.setEllipsize(TextUtils.TruncateAt.END)
        holder.comment.setMaxLines(DEFAULT_MAXLINE)
        var isExpand : Boolean = false

        holder.itemView.setOnClickListener{

            if(isExpand){

                holder.comment.setEllipsize(TextUtils.TruncateAt.END)
                isExpand = false
                val animation = ObjectAnimator.ofInt(holder.comment, "maxLines", DEFAULT_MAXLINE)
                animation.setDuration(200).start()
            }
            else{

                holder.comment.setEllipsize(null)
                isExpand = true
                MAXLINE = holder.comment.text.length/10 + 1
                val animation = ObjectAnimator.ofInt(holder.comment, "maxLines", MAXLINE)
                animation.setDuration(200).start()
            }
        }

    }

    override fun getItemCount(): Int {

        return comment.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val studentId: TextView = view.findViewById(R.id.studentID)
        val time: TextView = view.findViewById(R.id.time)
        val comment: TextView = view.findViewById(R.id.comment)
        val nickname : TextView = view.findViewById(R.id.nickname)
    }

    fun getTimeAgo(time: String): String {

        val simpleDataFormat = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val createTime = simpleDataFormat.parse(time).time
        val currentTime = System.currentTimeMillis()

        Log.i("TimeAgo", createTime.toString())
        Log.i("TimeAgo", currentTime.toString())
        Log.i("TimeAgo", DateUtils.getRelativeTimeSpanString(createTime, currentTime, DateUtils.MINUTE_IN_MILLIS).toString())
        return DateUtils.getRelativeTimeSpanString(createTime, currentTime, DateUtils.SECOND_IN_MILLIS).toString()
    }

}
