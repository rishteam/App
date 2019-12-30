package com.example.fju_course_registration_sys_rish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
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

        comFab.setOnClickListener{

            val intent = Intent(it.context, add_comment::class.java)
            intent.putExtra("cid", course.course_code)
            startActivity(intent)
        }



    }


    private fun animateFab(pos: Int){
        if( pos == 0 ){
            add_fab.show()
            comment_fab.hide()
        }
        else if( pos == 1 ){
            comment_fab.show()
            add_fab.hide()
        }
        else{
            add_fab.show()
            comment_fab.hide()
        }
    }

}
