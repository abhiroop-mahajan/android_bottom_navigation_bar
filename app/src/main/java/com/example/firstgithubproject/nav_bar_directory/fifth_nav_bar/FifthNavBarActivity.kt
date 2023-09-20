package com.example.firstgithubproject.nav_bar_directory.fifth_nav_bar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.firstgithubproject.R
import com.example.firstgithubproject.databinding.ActivityFifthNavBarBinding

class FifthNavBarActivity : AppCompatActivity(),FifthNavBarManager.DataSaver {
    private lateinit var binding: ActivityFifthNavBarBinding
    private lateinit var navBarDataSaver: FifthNavBarDataSaver
    private lateinit var navBarManager: FifthNavBarManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialise()
        navBarDataSaver =ViewModelProvider(this)[FifthNavBarDataSaver::class.java]
        navBarManager.updateNavBar(navBarDataSaver.getTab())
    }

    private fun initialise() {
        navBarManager = FifthNavBarManager(this,this,binding.navBar,supportFragmentManager)
    }

    override fun saveData(tab: Int) {
        navBarDataSaver.updateTab(tab)
    }
}