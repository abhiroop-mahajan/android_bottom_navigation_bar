package com.example.firstgithubproject.first_nav_bar

import android.view.View
import androidx.lifecycle.ViewModel

class FirstNavBarDataSaver:ViewModel() {
    private var activeTab = 1

    fun updateTab(tab:Int){
        activeTab =tab
    }

    fun getTab()=activeTab
}