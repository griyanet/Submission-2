package com.griyanet.submission2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.griyanet.submission2.R
import com.griyanet.submission2.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_userdetail.*

class Userdetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetail)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }
}