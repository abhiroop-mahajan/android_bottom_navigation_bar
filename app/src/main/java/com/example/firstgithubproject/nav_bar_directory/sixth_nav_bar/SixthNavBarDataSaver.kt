package com.example.firstgithubproject.nav_bar_directory.sixth_nav_bar

import androidx.lifecycle.ViewModel

class SixthNavBarDataSaver:ViewModel() {
    private var activeTab =1
    fun getTab()=activeTab
    fun updateTab(activeTab:Int){
        this.activeTab = activeTab
    }
}