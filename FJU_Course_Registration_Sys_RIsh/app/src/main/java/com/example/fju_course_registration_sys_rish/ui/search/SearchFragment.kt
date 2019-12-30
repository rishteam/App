package com.example.fju_course_registration_sys_rish.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.CourseData.Companion.courseData
import com.example.fju_course_registration_sys_rish.R
import com.example.fju_course_registration_sys_rish.ui.course.CourseFragment
import kotlinx.android.synthetic.main.courselist.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.department
import org.json.JSONArray

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    val item = arrayOf("None", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "E1", "E2", "E3")


    var kind : MutableMap<String, Boolean> = mutableMapOf(

        "必" to false,
        "選" to false,
        "通" to false,
        "輔" to false
    )


    val search : MutableMap<String, String> = mutableMapOf(
        "course_code" to "",
        "name"        to "",
        "teacher"     to "",
        "department"  to "",
        "period"      to ""
    )
    var day : MutableMap<String, Boolean> = mutableMapOf(

        "mon" to false,
        "tue" to false,
        "wed" to false,
        "thu" to false,
        "fri" to false,
        "sat" to false,
        "sun" to false
    )

    var overlap = false
    var url =""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val test = root.findViewById(R.id.test) as ConstraintLayout

        val spinner_layout = root.findViewById(R.id.spinner_layout) as LinearLayout
        val kind_layout = root.findViewById(R.id.kind_layout) as LinearLayout
        val spinner = root.findViewById(R.id.period_start) as Spinner
        val adapter = ArrayAdapter(spinner_layout.context, android.R.layout.simple_spinner_item, item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinnerEnd = root.findViewById(R.id.period_end) as Spinner
        val adapterEnd = ArrayAdapter(spinner_layout.context, android.R.layout.simple_spinner_item, item)
        adapterEnd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val navController = findNavController()


        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val choose = adapter.getItem(position)
                Log.i("onItem", choose)
                if(choose=="None"){

                    startTime.text = ""
                }
                else{

                    startTime.text = choose
                }
            }
        }
        spinnerEnd.adapter = adapterEnd
        spinnerEnd.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val choose = adapter.getItem(position)
                Log.i("onItem", choose)
                if(choose=="None"){

                    endTime.text = ""
                }
                else{

                    endTime.text = choose
                }
            }
        }


        val checkBox_overlap = root.findViewById(R.id.overlap) as CheckBox
        val checkBox_mon = root.findViewById(R.id.mon) as CheckBox
        val checkBox_tue = root.findViewById(R.id.tue) as CheckBox
        val checkBox_wed = root.findViewById(R.id.wed) as CheckBox
        val checkBox_thu = root.findViewById(R.id.thu) as CheckBox
        val checkBox_fri = root.findViewById(R.id.fri) as CheckBox
        val checkBox_sat = root.findViewById(R.id.sat) as CheckBox
        val checkBox_sun = root.findViewById(R.id.sun) as CheckBox
        val checkBox_major = root.findViewById(R.id.major) as CheckBox
        val checkBox_elective = root.findViewById(R.id.elective) as CheckBox
        val checkBox_general = root.findViewById(R.id.general) as CheckBox
        val checkBox_minor = root.findViewById(R.id.minor) as CheckBox



        val filter = root.findViewById(R.id.filterButton) as Button

        filter.setOnClickListener(object : View.OnClickListener{

            override fun onClick(v: View?) {


                search["name"] = Course_Name.text.toString()
                search["teacher"] = Professor.text.toString()
                search["course_code"] = cid.text.toString()
                search["department"] = department.text.toString()
                search["courseCode"] = cid.text.toString()

                var tmpStart = startTime.text.toString()
                var tmpEnd = endTime.text.toString()
                if(tmpEnd < tmpStart){

                    val tmp = tmpStart
                    tmpStart = tmpEnd
                    tmpEnd = tmp
                }

                if(tmpStart.isNotEmpty() && tmpEnd.isNotEmpty()){

                    search["period"] = tmpStart + "-" + tmpEnd
                }

                else if(tmpStart.isNotEmpty() && tmpEnd.isEmpty()){

                    search["period"] = tmpStart
                }
                else if(tmpStart.isEmpty() && tmpEnd.isNotEmpty()){

                    search["period"] = tmpEnd
                }


                if(checkBox_mon.isChecked()) day["mon"] = true
                if(checkBox_tue.isChecked()) day["tue"] = true
                if(checkBox_wed.isChecked()) day["wed"] = true
                if(checkBox_thu.isChecked()) day["thu"] = true
                if(checkBox_fri.isChecked()) day["fri"] = true
                if(checkBox_sat.isChecked()) day["sat"] = true
                if(checkBox_sun.isChecked()) day["sun"] = true
                if(checkBox_overlap.isChecked) overlap = true
                if(checkBox_major.isChecked()) kind["major"] = true
                if(checkBox_elective.isChecked()) kind["elective"] = true
                if(checkBox_general.isChecked()) kind["general"] = true
                if(checkBox_minor.isChecked()) kind["minor"] = true


                url = searchViewModel.getURL(search, day, overlap, kind)

                val que = Volley.newRequestQueue(spinner_layout.context)
                val req = JsonArrayRequest(Request.Method.GET, url, null,
                    Response.Listener<JSONArray> {
                            response ->

                        Log.i("response", response.toString())

                        if(response.length() > 0){

                            searchViewModel.loadData(response)

                            courseData = searchViewModel.getList()
                            navController.navigate(R.id.nav_course)
                        }
                        else{

                            Toast.makeText(spinner_layout.context, "查無此課程", Toast.LENGTH_SHORT).show()
                        }

                    },
                    Response.ErrorListener { error->

                        Log.e("ResponseError", error.toString())
                    })
                que.add(req)

                day["mon"] = false
                day["tue"] = false
                day["wed"] = false
                day["thu"] = false
                day["fri"] = false
                day["sat"] = false
                day["sun"] = false
                overlap = false

                kind["major"] = false
                kind["elective"] = false
                kind["general"] = false
                kind["minor"] = false
                Log.i("searchaaa", url)
            }

        })

        return root
    }




}