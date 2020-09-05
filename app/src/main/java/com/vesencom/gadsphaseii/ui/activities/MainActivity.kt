package com.vesencom.gadsphaseii.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.vesencom.gadsphaseii.R
import com.vesencom.gadsphaseii.adapters.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        support fragment manager passes the instance of fragment manager to the adapter
        view_pager.adapter = SectionsPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(view_pager)


    }

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