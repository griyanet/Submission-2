package com.griyanet.submission2

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.griyanet.submission2.Adapter.UserAdapter
import com.griyanet.submission2.Adapter.UserQueryAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var countdownTimer: CountDownTimer
    private var seconds = 3L
    private lateinit var viewmodel: MainViewModel
    private val myAdapter by lazy { UserAdapter() }
    private val queryAdapter by lazy { UserQueryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitRequest()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                submitUserQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    private fun submitRequest() {
        showLoading(true)
        setupRecyclerView()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewmodel.getUser()
        viewmodel.usersResponse.observe(this, { response ->
            if (response.isSuccessful) {
                fadeIn()
                showLoading(false)
                response.body()?.let { myAdapter.setData(it) }
            } else {
                Log.d("MainActivity", "Something wrong..... response code: ${response.code()}")
                showLoading(true)
                attemptReqAgain(seconds)
            }
        })
    }

    private fun submitUserQuery(username: String) {
        showLoading(true)
        setupQueryRecyclerView()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewmodel.getUserQuery(username)
        viewmodel.userQuery.observe(this, { response ->
            if (response.isSuccessful) {
                fadeIn()
                showLoading(false)
                response.body()?.items.let {
                    if (it != null) {
                        queryAdapter.setData(it)
                    }
                }
            } else {
                Log.d("MainActivity", "Something wrong..... response code: ${response.code()}")
                showLoading(true)
                attemptReqAgain(seconds)
            }
        })
    }

    private fun setupRecyclerView() {
        rv_user.setHasFixedSize(true)
        rv_user.adapter = myAdapter
        rv_user.layoutManager = LinearLayoutManager(this)
    }

    private fun setupQueryRecyclerView() {
        rv_user.setHasFixedSize(true)
        rv_user.adapter = queryAdapter
        rv_user.layoutManager = LinearLayoutManager(this)
    }


    private fun fadeIn() {
        v_blackScreen.animate().apply {
            alpha(0f)
            duration = 3000
        }.start()
    }

    private fun attemptReqAgain(seconds: Long) {
        countdownTimer = object : CountDownTimer(seconds * 1010, 1000) {
            override fun onFinish() {
                submitRequest()
                countdownTimer.cancel()
                tv_noInternetCountDown.visibility = View.GONE
                this@MainActivity.seconds+=3
            }

            override fun onTick(milsecTilFinish: Long) {
                tv_noInternetCountDown.visibility = View.VISIBLE
                tv_noInternetCountDown.text = getString(R.string.no_Internet)
                Log.d("MainActivity", "Could not retrieve data. Trying again in: ${milsecTilFinish/1000} seconds")
            }
        }
        countdownTimer.start()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

}