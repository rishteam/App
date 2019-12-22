package com.example.fju_course_registration_sys_rish

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fju_course_registration_sys_rish.ui.course.Course

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)
class SectionsPagerAdapter(fm: FragmentManager,private val data: Course) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {

            0 -> InfoFragment(data)
            else -> CommentFragment(data.course_code)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {

            0 -> "Info"
            else -> "Comment"

        }
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}