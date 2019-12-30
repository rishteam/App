package com.example.fju_course_registration_sys_rish

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class CommentFragment(private val cid : String) : Fragment() {
    // TODO: Rename and change types of parameters
        var url = "http://vm.rish.com.tw/db/v1/comments?cid=" + cid
    val commentList_ : MutableList<Comment> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_comment, container, false)
        val commentList = root.findViewById(R.id.commentList) as RecyclerView
        val frame = root.findViewById(R.id.frame) as LinearLayout
        val swipe = root.findViewById(R.id.swipe) as SwipeRefreshLayout

        val que = Volley.newRequestQueue(frame.context)
        val req = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONArray> {
                    response ->

                Log.i("comment", response.toString())
                Log.i("comment", response.length().toString())
                commentList_.clear()
                for( i in 0 until response.length()){

                    val comment : Comment = Comment()
                    comment.loadData(response.getJSONObject(i))
                    commentList_.add(comment)
                }
                commentList.layoutManager = LinearLayoutManager(frame.context)
                commentList.adapter = CommentListViewAdapter(commentList_)
            },
            Response.ErrorListener { error->

                Toast.makeText(frame.context, "No Comment", Toast.LENGTH_SHORT).show()

                val comment: Comment = Comment()
                comment.emptyLoad()
                commentList_.add(comment)
                commentList.layoutManager = LinearLayoutManager(frame.context)
                commentList.adapter = CommentListViewAdapter(commentList_)

                Log.e("ResponseError", error.toString())
            })
        que.add(req)

        swipe.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(frame.context, R.color.colorPrimary))
        swipe.setColorSchemeColors(Color.WHITE)

        swipe.setOnRefreshListener{

            commentList_.clear()
            val que = Volley.newRequestQueue(frame.context)
            val req = JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener<JSONArray> {
                        response ->

                    Log.i("commentfresh", response.toString())
                    Log.i("commentfresh", response.length().toString())
                    commentList_.clear()
                    for( i in 0 until response.length()){

                        val comment : Comment = Comment()
                        comment.loadData(response.getJSONObject(i))
                        commentList_.add(comment)
                    }
                    commentList.layoutManager = LinearLayoutManager(frame.context)
                    commentList.adapter = CommentListViewAdapter(commentList_)

                    swipe.isRefreshing = false
                },
                Response.ErrorListener { error->

                    Toast.makeText(frame.context, "No Comment", Toast.LENGTH_SHORT).show()

                    val comment: Comment = Comment()
                    comment.emptyLoad()
                    commentList_.add(comment)
                    commentList.layoutManager = LinearLayoutManager(frame.context)
                    commentList.adapter = CommentListViewAdapter(commentList_)

                    Log.e("ResponseError", error.toString())
                })
            que.add(req)
        }


        return root
    }
}
