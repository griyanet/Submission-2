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
import kotlinx.android.synthetic.main.fragment_follower.*
import kotlinx.android.synthetic.main.user_fragment.progressBar
import kotlinx.android.synthetic.main.user_fragment.v_blackScreen

class FollowerFragment : Fragment() {

    companion object {
        const val ARG_USERNAME = "username"

        fun newInstance(username: String): FollowerFragment {
            val fragment = FollowerFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var viewModel: MainViewModel
    private val userAdapter by lazy { FollowerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(ARG_USERNAME)
        Log.d(username, "username")

        rv_userFollower.layoutManager = LinearLayoutManager(activity)
        rv_userFollower.setHasFixedSize(true)
        rv_userFollower.adapter = userAdapter

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        if (username != null) {
            viewModel.getUserFollower(username)
        }
        viewModel.userFollower.observe(viewLifecycleOwner, { response ->
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