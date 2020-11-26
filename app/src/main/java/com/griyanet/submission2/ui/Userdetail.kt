package com.griyanet.submission2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.griyanet.submission2.MainViewModel
import com.griyanet.submission2.MainViewModelFactory
import com.griyanet.submission2.R
import com.griyanet.submission2.Repository
import com.griyanet.submission2.adapter.SectionsPagerAdapter
import com.griyanet.submission2.model.Item
import kotlinx.android.synthetic.main.activity_userdetail.*

class Userdetail : AppCompatActivity() {

    companion object {
        const val USERNAME = "username"
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetail)

        val intent = intent
        val item = intent.getParcelableExtra<Item>(USERNAME)
        val username = item?.login

        val adapter = SectionsPagerAdapter(this, supportFragmentManager)
        adapter.username = username
        view_pager.adapter = adapter
        adapter.addFragment(FollowerFragment(), "Follower")
        adapter.addFragment(FollowingFragment(), "Following")
        tabs.setupWithViewPager(view_pager)
        adapter.notifyDataSetChanged()

        supportActionBar?.elevation = 0f

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        if (username != null) {
            viewModel.getUserDetail(username)
        }
        viewModel.userDetails.observe(this, { response ->
            response.body()?.let {
                Glide.with(this)
                    .load(it.avatarUrl)
                    .into(img_avatar)
                tv_loginId.text = it.login
                tv_name.text = it.name
                tv_location.text = it.location
                tv_follower.text = it.followers.toString()
                tv_following.text = it.following.toString()
                tv_company.text = it.company
            }
        })
    }
}