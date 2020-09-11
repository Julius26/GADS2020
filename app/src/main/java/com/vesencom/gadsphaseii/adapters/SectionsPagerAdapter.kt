package com.vesencom.gadsphaseii.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.vesencom.gadsphaseii.ui.fragments.IQFragment
import com.vesencom.gadsphaseii.ui.fragments.TopLearners


class SectionsPagerAdapter(fragmentManager: FragmentManager):
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val fragments = listOf(TopLearners(), IQFragment())
    private val titles = listOf("Learning Leaders", "Skill IQ Leaders")
    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}