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

class CourseFragment(var flag: Boolean = false, var url : String = "http://vm.rish.com.tw/db/v1/fju_course/courses?") : Fragment() {

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

        val button = root.findViewById(R.id.filter) as MaterialButton
        val result = root.findViewById(R.id.result) as LinearLayout
//        val toolLayout = root.findViewById(R.id.toolLayout) as LinearLayout
        val listView = root.findViewById(R.id.listView) as RecyclerView
        val tb = root.findViewById(R.id.aaa) as Toolbar
        val sv = root.findViewById(R.id.searchView) as SearchView

        Log.i("child", "HI")

        tb.setOnClickListener {

            Toast.makeText(toolLayout.context, "hih", Toast.LENGTH_SHORT).show()
            val testFragment : SearchFragment = SearchFragment()
            val fra = getFragmentManager()
            val transaction = fra!!.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, testFragment)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit()
        }
//
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                Toast.makeText(toolLayout.context, "123", Toast.LENGTH_SHORT).show()
                url += "teacher=" + query
                flag = true
                Log.i("searchView", url)
                req(url)
                return false
            }
        })


        if(flag){

            val que = Volley.newRequestQueue(result.context)
                val req = JsonArrayRequest(Request.Method.GET, url, null,
                    Response.Listener<JSONArray> {
                            response ->

                        result.removeAllViews()
                        result.refreshDrawableState()

                        Log.i("response", response.toString())

                        courseViewModel.loadData(response)
                        Log.i("LoadData", courseViewModel.getList().toString())
                        Log.i("uuu", courseViewModel.getDataLen().toString())

                        listView.layoutManager = LinearLayoutManager(result.context)
                        listView.adapter = RecyclerListViewAdapter(courseViewModel.getList(), result.context)

                        result.addView(listView)
                    },
                    Response.ErrorListener { error->

                        Log.e("ResponseError", error.toString())
                    })
                que.add(req)
            }





//        button.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View) {
//
////                search["name"] = Course_name.text.toString()
////                search["teacher"] = Professor.text.toString()
//
//                /*
//                search["course_code"]
//                search["department"]
//                search["score"]
//                search["kind"]
//                search["times"]
//                search["day"]
//                search["week"]
//                search["period"]
//                search["classroom"]
//                  */
//
//                url = courseViewModel.getURL(search)
//                Log.i("2345677", url)
//
//                val que = Volley.newRequestQueue(result.context)
//                val req = JsonArrayRequest(Request.Method.GET, url, null,
//                    Response.Listener<JSONArray> {
//                            response ->
//
//                        result.removeAllViews()
//                        result.refreshDrawableState()
//
//                        Log.i("response", response.toString())
//
//                        courseViewModel.loadData(response)
//                        Log.i("LoadData", courseViewModel.getList().toString())
//                        Log.i("uuu", courseViewModel.getDataLen().toString())
//
//                        listView.layoutManager = LinearLayoutManager(result.context)
//                        listView.adapter = RecyclerListViewAdapter(courseViewModel.getList(), result.context)
//
//                        result.addView(listView)
//                    },
//                    Response.ErrorListener { error->
//
//                        Log.e("ResponseError", error.toString())
//                    })
//                que.add(req)
//            }
//        })
        flag = false
        return root
    }

    fun req(url: String){

        val que = Volley.newRequestQueue(result.context)
        val req = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener<JSONArray> {
                    response ->

                result.removeAllViews()
                result.refreshDrawableState()

                Log.i("response", response.toString())

                courseViewModel.loadData(response)
                Log.i("LoadData", courseViewModel.getList().toString())
                Log.i("uuu", courseViewModel.getDataLen().toString())

                listView.layoutManager = LinearLayoutManager(result.context)
                listView.adapter = RecyclerListViewAdapter(courseViewModel.getList(), result.context)

                result.addView(listView)
            },
            Response.ErrorListener { error->

                Log.e("ResponseError", error.toString())
            })
        que.add(req)
    }

}


