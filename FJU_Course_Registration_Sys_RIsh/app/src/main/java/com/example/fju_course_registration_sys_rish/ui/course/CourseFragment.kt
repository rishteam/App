package com.example.fju_course_registration_sys_rish.ui.course

import android.graphics.Color
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
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.ui.search.SearchFragment
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_course.*
import org.json.JSONArray

class CourseFragment(val course: MutableList<Course> = arrayListOf()) : Fragment() {

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

        if(course.isNotEmpty())
            Log.i("child", course[0].professor)

        tb.setOnClickListener {

            Toast.makeText(toolLayout.context, "hih", Toast.LENGTH_SHORT).show()
            val testFragment : SearchFragment = SearchFragment()
            val fra = getFragmentManager()
            val transaction = fra!!.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, testFragment)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
        }

        if(course.isNotEmpty()){

            listView.layoutManager = LinearLayoutManager(result.context)
            listView.adapter = RecyclerListViewAdapter(course, result.context)
        }
        return root
    }

}


