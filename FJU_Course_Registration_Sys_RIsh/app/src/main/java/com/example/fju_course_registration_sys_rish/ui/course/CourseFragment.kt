package com.example.fju_course_registration_sys_rish.ui.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fju_course_registration_sys_rish.R
import android.util.Log
import android.view.Gravity
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fju_course_registration_sys_rish.CourseData.Companion.courseData
import kotlinx.android.synthetic.main.fragment_course.*

class CourseFragment(var course: MutableList<Course> = arrayListOf()) : Fragment() {

    private lateinit var courseViewModel: CourseViewModel
    val search : MutableMap<String, String> = mutableMapOf(
        "course_code" to "",
        "name"        to "",
        "teacher"     to "",
        "department"  to "",
        "score"       to "",
        "kind"        to "",
        "times"       to "",
        "day"         to "",
        "week"        to "",
        "period"      to "",
        "classroom"   to ""
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        courseViewModel =
            ViewModelProviders.of(this).get(CourseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_course, container, false)

//        val button = root.findViewById(R.id.filter) as MaterialButton
        val result = root.findViewById(R.id.result) as LinearLayout
//        val toolLayout = root.findViewById(R.id.toolLayout) as LinearLayout
        val listView = root.findViewById(R.id.listView) as RecyclerView
        val tb = root.findViewById(R.id.aaa) as Toolbar
//        val sv = root.findViewById(R.id.searchView) as SearchView
        val navController = findNavController()

        tb.setOnClickListener {

            navController.navigate(R.id.nav_search)
        }
        course = courseData

        if(course.isNotEmpty()){

            listView.layoutManager = LinearLayoutManager(result.context)
            listView.adapter = RecyclerListViewAdapter(course, result.context)
        }
        return root
    }

}


