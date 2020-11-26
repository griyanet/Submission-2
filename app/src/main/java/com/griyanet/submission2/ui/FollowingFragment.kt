package com.griyanet.submission2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.griyanet.submission2.MainViewModel
import com.griyanet.submission2.MainViewModelFactory
import com.griyanet.submission2.R
import com.griyanet.submission2.Repository
import com.griyanet.submission2.adapter.FollowerAdapter
import kotlinx.android.synthetic.main.fragment_follower.progressBar
import kotlinx.android.synthetic.main.fragment_follower.v_blackScreen
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {

    companion object {
        private const val ARG_USERNAME = "username"
        fun newInstance(username: String) =
            FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                }
            }
    }

    private lateinit var viewModel: MainViewModel
    private val userAdapter by lazy { FollowerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(FollowerFragment.ARG_USERNAME)
        Log.d(username, "username")

        rv_userFollowing.layoutManager = LinearLayoutManager(activity)
        rv_userFollowing.setHasFixedSize(true)
        rv_userFollowing.adapter = userAdapter

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        if (username != null) {
            viewModel.getUserFollowing(username)
        }
        viewModel.userFollowing.observe(viewLifecycleOwner, { response ->
            response.body().let {
                if (it != null) {
                    userAdapter.setData(it)
                }
            }
        })


        fadeIn()
        viewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun fadeIn() {
        v_blackScreen.animate().apply {
            alpha(0f)
            duration = 3000
        }.start()
    }
}