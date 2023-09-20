package com.example.firstgithubproject.nav_bar_directory.fourth_nav_bar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.firstgithubproject.R
import com.example.firstgithubproject.databinding.ActivityFourthNavBarBinding

class FourthNavBarActivity : AppCompatActivity() ,FourthNavManager.SaveData{
    private lateinit var binding: ActivityFourthNavBarBinding
    private lateinit var navManager: FourthNavManager
    private lateinit var dataSaver: FourthNavBarDataSaver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialise()
        initialiseViewModel()
        initialiseData()
    }

    private fun initialiseData() {
        navManager.updateNavBarStatus(dataSaver.getTab())
    }

    private fun initialiseViewModel() {
        dataSaver = ViewModelProvider(this)[FourthNavBarDataSaver::class.java]
    }

    private fun initialise() {
        navManager = FourthNavManager(this,this,supportFragmentManager,binding.navBar)
    }

    override fun saveData(tab: Int) {
        dataSaver.updateTab(tab)
    }


}