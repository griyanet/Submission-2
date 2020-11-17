package com.griyanet.submission2

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.griyanet.submission2.Model.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var countdownTimer: CountDownTimer
    private var seconds = 3L
    private lateinit var viewmodel: MainViewModel
    private val myAdapter by lazy {UserAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitRequest()
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

    private fun setupRecyclerView() {
        rv_user.setHasFixedSize(true)
        rv_user.adapter = myAdapter
        rv_user.layoutManager = LinearLayoutManager(this)
    }

    private fun fadeIn() {
        v_blackScreen.animate().apply {
            alpha(0f)
            duration = 3000
        }.start()
    }

    private fun attemptReqAgain(seconds: Long) {
        countdownTimer = object: CountDownTimer(seconds*1010, 1000) {
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