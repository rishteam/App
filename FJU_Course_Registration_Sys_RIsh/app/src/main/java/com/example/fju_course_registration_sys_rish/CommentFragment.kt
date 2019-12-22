package com.example.fju_course_registration_sys_rish

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val que = Volley.newRequestQueue(frame.context)
        Log.i("comment", url)
        val req = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONArray> {
                    response ->
                Log.i("comment", response.toString())

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

                Log.e("ResponseError", error.toString())
            })
        que.add(req)

//
//        commentList.layoutManager = LinearLayoutManager(frame.context)
//        commentList.adapter = CommentListViewAdapter(commentList_)
//
//        frame.addView(commentList)
        // Inflate the layout for this fragment
        return root


    }
}
