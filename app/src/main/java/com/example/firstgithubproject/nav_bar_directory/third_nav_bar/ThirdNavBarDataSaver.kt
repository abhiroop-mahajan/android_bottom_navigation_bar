package com.example.firstgithubproject.nav_bar_directory.third_nav_bar

import androidx.lifecycle.ViewModel

class ThirdNavBarDataSaver:ViewModel() {
    private var activeTab=1

    fun updateTab(tab:Int){
        activeTab =tab
    }

    fun getTab()=activeTab
}