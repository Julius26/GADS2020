package com.vesencom.gadsphaseii.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.vesencom.gadsphaseii.R
import com.vesencom.gadsphaseii.adapters.SectionsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val sectionsPagerAdapter: SectionsPagerAdapter by lazy {
        SectionsPagerAdapter(supportFragmentManager)
    }


    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        initUi()
//        toolbar.title = getString(R.string.toolbar_title)
//        setSupportActionBar(toolbar)
//        initListeners()

        tabs.setupWithViewPager(fragmentPager)
        fragmentPager.adapter = sectionsPagerAdapter
    }

//    private fun initListeners(){
//        initSubmitButton.setOnClickListener { initSubmitActivity() }
//    }
//    private fun initUi(){
//        tabs.setupWithViewPager(fragmentPager)
//        fragmentPager.adapter = mainPagerAdapter
//    }

//    private fun initSubmitActivity(){
//        startActivity(Intent(this, SubmitActivity::class.java))
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate((R.menu.submit_menu), menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.submitButton -> {
                startActivity(Intent(this, SubmitActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}