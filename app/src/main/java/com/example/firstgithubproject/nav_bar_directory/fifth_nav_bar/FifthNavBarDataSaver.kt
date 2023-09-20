package com.example.firstgithubproject.nav_bar_directory.fifth_nav_bar

import androidx.lifecycle.ViewModel

class FifthNavBarDataSaver:ViewModel() {
    private var activeTab:Int =1

    fun getTab()=activeTab
    fun updateTab(tab:Int){
        activeTab =tab
    }
}