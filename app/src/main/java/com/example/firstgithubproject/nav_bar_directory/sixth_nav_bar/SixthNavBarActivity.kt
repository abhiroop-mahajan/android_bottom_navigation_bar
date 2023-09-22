package com.example.firstgithubproject.nav_bar_directory.sixth_nav_bar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.firstgithubproject.R
import com.example.firstgithubproject.databinding.ActivitySixthNavBarBinding

class SixthNavBarActivity : AppCompatActivity() ,SixthNavBarManager.SaveData{
    private lateinit var binding:ActivitySixthNavBarBinding
    private lateinit var manager: SixthNavBarManager
    private lateinit var dataSaver: SixthNavBarDataSaver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixthNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialiseManager()
        initialiseDataSaver()
        manager.updateTab(dataSaver.getTab())
    }

    private fun initialiseManager() {
        manager =SixthNavBarManager(this,supportFragmentManager,binding.navBar,this,)
    }

    private fun initialiseDataSaver(){
        dataSaver = ViewModelProvider(this)[SixthNavBarDataSaver::class.java]
    }

    override fun saveData(tab: Int) {
        dataSaver.updateTab(tab)
    }

}