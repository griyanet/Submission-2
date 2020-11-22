package com.griyanet.submission2.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.griyanet.submission2.Adapter.UserAdapter
import com.griyanet.submission2.MainViewModel
import com.griyanet.submission2.MainViewModelFactory
import com.griyanet.submission2.Model.UserItem
import com.griyanet.submission2.R
import com.griyanet.submission2.Repository
import kotlinx.android.synthetic.main.user_fragment.*
import retrofit2.Response

class UserFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var listUser: MutableList<Response<ArrayList<UserItem>>> = mutableListOf()
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_user.setHasFixedSize(true)
        rv_user.adapter = userAdapter
        rv_user.layoutManager = LinearLayoutManager(context)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getUser()
        viewModel.usersResponse.observe(viewLifecycleOwner, Observer {
            it.body()?.let {
                if (it != null) {
                    userAdapter.setData(it)
                }
            }
        })
        viewModel.usersResponse.observe(viewLifecycleOwner, Observer {
            it.let {
                listUser.clear()
                listUser.addAll(listOf(it))
                userAdapter.notifyDataSetChanged()
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

