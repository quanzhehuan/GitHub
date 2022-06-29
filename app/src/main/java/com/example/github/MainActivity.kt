package com.example.github

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.github.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //setContentView(R.layout.activity_main)
        val viewModel = makeApiCall()
        setupBinding(viewModel)
        viewModel.getAdapter().setOnItemClickListener(object : UserViewAdapter.onItemClickListener {
            override fun onItemClick(userData: UserData) {
                val intent = Intent(this@MainActivity, UserInfoActivity::class.java)
                intent.putExtra("login", userData.login)
                intent.putExtra("avatar_url", userData.avatar_url)
                startActivity(intent)
            }
        })
    }

    fun setupBinding(viewModel : MainActivityViewModel) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val activityMainBinding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.setVariable(BR.viewModel, viewModel)
        activityMainBinding.executePendingBindings()

        userListView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }

    }

    fun makeApiCall() : MainActivityViewModel{
        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListDataObserver().observe(this, Observer<ArrayList<UserData>> {
            progressBar.visibility = GONE
            if(it != null){
                //MAJ de adapter
                viewModel.setAdapterData(it)
            }else{
                Toast.makeText(this@MainActivity, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeAPICall()

        return viewModel
    }
}