package com.jortw.adventurer.ui.main

import android.content.Context
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jortw.adventurer.FindAGameFragment
import com.jortw.adventurer.ListAGameFragment
import com.jortw.adventurer.R


private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2

)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment{
        var fragment: Fragment? = null

        if(position == 0){
            fragment = FindAGameFragment.newInstance()
        }
        if (position == 1){
            fragment = ListAGameFragment.newInstance()
        }
        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}