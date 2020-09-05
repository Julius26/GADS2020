package com.vesencom.gadsphaseii.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.vesencom.gadsphaseii.ui.fragments.IQFragment
import com.vesencom.gadsphaseii.ui.fragments.TopLearners


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
//fragment manager passes the same value to the super class
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){



    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {
                TopLearners()
            }
            1 -> {
                IQFragment()
            }
            else -> { return TopLearners() }
        }
        return TopLearners()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {return "Learning Leaders"}
            1 -> {return "Skill IQ Leaders"}
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}