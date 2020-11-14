package com.griyanet.submission2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.griyanet.submission2.Model.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: MainViewModel
    private val myAdapter by lazy {UserAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewmodel.getUser()
        viewmodel.myResponse.observe(this, Observer { response ->
            Log.d("Response", response.body()?.)
        })



    }
}