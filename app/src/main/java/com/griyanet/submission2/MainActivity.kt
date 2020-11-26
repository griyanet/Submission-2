package com.griyanet.submission2

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.griyanet.submission2.adapter.UserQueryAdapter
import com.griyanet.submission2.model.Item
import com.griyanet.submission2.ui.Userdetail
import kotlinx.android.synthetic.main.user_fragment.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val USERNAME = "username"
    }

    private var listUserQuery: MutableList<Item> = mutableListOf()
    private lateinit var viewModel: MainViewModel
    private lateinit var userAdapter: UserQueryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userAdapter = UserQueryAdapter(this, listUserQuery) {
            val intent = Intent(this, Userdetail::class.java)
            intent.putExtra(USERNAME, it)
            startActivity(intent)
        }

        rv_user.setHasFixedSize(true)
        rv_user.adapter = userAdapter
        rv_user.layoutManager = LinearLayoutManager(this)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getUserQuery("a")
        viewModel.userQuery.observe(this, { response ->
            response.body()?.items.let {
                if (it != null) {
                    listUserQuery.clear()
                    listUserQuery.addAll(it)
                    userAdapter.notifyDataSetChanged()
                }
            }
        })
        fadeIn()
        viewModel.loading.observe(this, {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

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
                goUserQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    fun goUserQuery(query: String) {
        viewModel.getUserQuery(query)
        viewModel.userQuery.observe(this, { response ->
            response.body()?.items.let {
                if (it != null) {
                    listUserQuery.clear()
                    listUserQuery.addAll(it)
                    userAdapter.notifyDataSetChanged()
                }
            }
        })
        fadeIn()
        viewModel.loading.observe(this, {
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