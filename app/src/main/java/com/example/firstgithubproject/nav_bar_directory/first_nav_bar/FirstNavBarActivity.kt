package com.example.firstgithubproject.nav_bar_directory.first_nav_bar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firstgithubproject.R
import com.example.firstgithubproject.databinding.ActivityFirstNavBarBinding

class FirstNavBarActivity : AppCompatActivity() , FirstNavigationManager.DataSaver {
    private lateinit var binding: ActivityFirstNavBarBinding
    private lateinit var navManager: FirstNavigationManager
    private lateinit var dataSaver: FirstNavBarDataSaver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFirstNavBarBinding.inflate(layoutInflater)
        navManager = FirstNavigationManager(this,this,binding.navBar,supportFragmentManager)
        dataSaver =ViewModelProvider(this)[FirstNavBarDataSaver::class.java]
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