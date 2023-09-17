package com.example.firstgithubproject.nav_bar_directory.second_nav_bar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.firstgithubproject.databinding.ActivitySecondNavBarBinding

class SecondNavBarActivity : AppCompatActivity(), SecondNavigationManager.DataSaver {
    private lateinit var binding: ActivitySecondNavBarBinding
    private lateinit var navManager: SecondNavigationManager
    private lateinit var dataSaver: SecondNavBarDataSaver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondNavBarBinding.inflate(layoutInflater)
        navManager = SecondNavigationManager(this, this, binding.navBar, supportFragmentManager)
        dataSaver = ViewModelProvider(this)[SecondNavBarDataSaver::class.java]
        setNavBarInitially()
        setContentView(binding.root)
    }

    private fun setNavBarInitially() {
        navManager.updateNavBar(dataSaver.getTab())
    }

    override fun saveData(tab: Int) {
        dataSaver.updateTab(tab)
    }


}