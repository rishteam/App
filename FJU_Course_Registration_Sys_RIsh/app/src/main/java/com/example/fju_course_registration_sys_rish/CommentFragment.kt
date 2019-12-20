package com.example.fju_course_registration_sys_rish

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommentFragment : Fragment() {
    // TODO: Rename and change types of parameters

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
        commentList.layoutManager = LinearLayoutManager(frame.context)
        commentList.adapter = CommentListViewAdapter()
//
//        frame.addView(commentList)
        // Inflate the layout for this fragment
        return root


    }
}
