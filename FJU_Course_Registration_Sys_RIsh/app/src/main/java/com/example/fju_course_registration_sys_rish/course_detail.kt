package com.example.fju_course_registration_sys_rish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.fju_course_registration_sys_rish.ui.course.Course
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.course_activity.*



class Course_detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_activity)

        val course = intent.getSerializableExtra("Data") as Course

        val sectionsPagerAdapter =
            SectionsPagerAdapter(supportFragmentManager, course)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val addFab: FloatingActionButton = findViewById(R.id.add_fab)
        val comFab: FloatingActionButton = findViewById(R.id.comment_fab)

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                animateFab(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

        addFab.setOnClickListener {
            if( !isFABOpen ){
                showFABmenu()
            }
            else{
                closeFABmenu()
            }
        }

        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add)
        val fabDel: FloatingActionButton = findViewById(R.id.fab_del)

        fabAdd.setOnClickListener {
            closeFABmenu()
            val url = "http://vm.rish.com.tw/db/v1/fju_course/"+ UserData.ldapUser+"/"+course.course_code
            val que = Volley.newRequestQueue(it.context)
            val reqGra = object : JsonObjectRequest(
                Request.Method.POST, url,null,
                Response.Listener { response ->
                    Toast.makeText(it.context,"ADD success",Toast.LENGTH_SHORT).show()
                    Log.i("Fab(ADD/DEL)","ADD Success")
                },
                Response.ErrorListener { error ->
                    Toast.makeText(it.context,"ADD fail",Toast.LENGTH_SHORT).show()
                    Log.i("Fab(ADD/DEL)","ADD Fail")
                })
            {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    val authorization = "Digest " + UserData.ldapToken
                    headers["Authorization"] = authorization
                    return headers
                }
            }
            que.add(reqGra)
        }
        fabDel.setOnClickListener {
            closeFABmenu()
            val url = "http://vm.rish.com.tw/db/v1/fju_course/"+ UserData.ldapUser+"/"+course.course_code
            val que = Volley.newRequestQueue(it.context)
            val reqGra = object : JsonObjectRequest(
                Request.Method.DELETE, url,null,
                Response.Listener { response ->
                    Toast.makeText(it.context,"DELETE success",Toast.LENGTH_SHORT).show()
                    Log.i("Fab(ADD/DEL)","Del Success")
                },
                Response.ErrorListener { error ->
                    Toast.makeText(it.context,"DELETE fail",Toast.LENGTH_SHORT).show()
                    Log.i("Fab(ADD/DEL)","Del Fail")
                })
            {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    val authorization = "Digest " + UserData.ldapToken
                    headers["Authorization"] = authorization
                    return headers
                }
            }
            que.add(reqGra)
        }

        comFab.setOnClickListener{

            val intent = Intent(it.context, add_comment::class.java)
            intent.putExtra("cid", course.course_code)
            startActivity(intent)
        }



    }

    private var isFABOpen = false
    private fun showFABmenu(){
        isFABOpen = true
        fab_add.animate().translationY(-getResources().getDimension(R.dimen.standard_125))
        fab_del.animate().translationY(-getResources().getDimension(R.dimen.standard_65))
    }
    private fun closeFABmenu(){
        isFABOpen = false
        fab_add.animate().translationY(0F)
        fab_del.animate().translationY(0F)
    }

    private fun animateFab(pos: Int){
        if( pos == 0 ){
            add_fab.show()
            comment_fab.hide()
        }
        else if( pos == 1 ){
            comment_fab.show()
            add_fab.hide()
            closeFABmenu()
        }
        else{
            add_fab.show()
            comment_fab.hide()
        }
    }

}
