package com.example.firstgithubproject.nav_bar_directory.third_nav_bar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

import com.example.firstgithubproject.databinding.ActivityThirdNavBarBinding

class ThirdNavBarActivity : AppCompatActivity() ,ThirdNavBarManager.SaveData{
    private lateinit var binding: ActivityThirdNavBarBinding
    private lateinit var navBarManager: ThirdNavBarManager
    private lateinit var dataSaver: ThirdNavBarDataSaver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityThirdNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeViewModel()
        initializeNavManager()
        updateInitialNavBar()
    }

    private fun updateInitialNavBar() {
        navBarManager.updateNavBarStatus(dataSaver.getTab())
    }

    private fun initializeViewModel() {
        dataSaver = ViewModelProvider(this)[ThirdNavBarDataSaver::class.java]
    }

    private fun initializeNavManager() {
        navBarManager = ThirdNavBarManager(this,this,supportFragmentManager,binding.navBar)
    }

    override fun saveData(tab: Int) {
        dataSaver.updateTab(tab)
    }
}