package com.griyanet.submission2.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.griyanet.submission2.Adapter.UserQueryAdapter
import com.griyanet.submission2.MainViewModel
import com.griyanet.submission2.MainViewModelFactory
import com.griyanet.submission2.R
import com.griyanet.submission2.Repository
import kotlinx.android.synthetic.main.user_fragment.*

class UserQueryFragment : Fragment() {

    companion object {
        fun newInstance() = UserQueryFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val userAdapter by lazy { UserQueryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_query_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_user.setHasFixedSize(true)
        rv_user.adapter = userAdapter
        rv_user.layoutManager = LinearLayoutManager(context)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        //viewModel.getUserQuery()
        viewModel.userQuery.observe(viewLifecycleOwner, {
            it.body()?.items.let {
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



