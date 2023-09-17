package com.example.firstgithubproject.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstgithubproject.R
import com.example.firstgithubproject.databinding.ActivityMainBinding
import com.example.firstgithubproject.nav_bar_directory.second_nav_bar.SecondNavBarActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataList = ArrayList<AdapterModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setList()
        setAdapter()
    }

    private fun setAdapter() {
        binding.rvItems.layoutManager =LinearLayoutManager(this)
        binding.rvItems.adapter = ItemsAdapter(dataList){position->
            startActivity(Intent(this, SecondNavBarActivity::class.java))
        }
    }

    private fun setList() {
        dataList.add(AdapterModel("First Navigation Bar", R.drawable.ic_nav_1))
        dataList.add(AdapterModel("Second Navigation Bar", R.drawable.ic_nav_2))
        dataList.add(AdapterModel("Third Navigation Bar", R.drawable.ic_nav_1))
        dataList.add(AdapterModel("Fourth Navigation Bar", R.drawable.ic_nav_1))
        dataList.add(AdapterModel("Fifth Navigation Bar", R.drawable.ic_nav_1))
        dataList.add(AdapterModel("Sixth Navigation Bar", R.drawable.ic_nav_1))
        dataList.add(AdapterModel("Seventh Navigation Bar", R.drawable.ic_nav_1))
        dataList.add(AdapterModel("Eighth Navigation Bar", R.drawable.ic_nav_1))
        dataList.add(AdapterModel("Ninth Navigation Bar", R.drawable.ic_nav_1))
        dataList.add(AdapterModel("Tenth Navigation Bar", R.drawable.ic_nav_1))
    }

}