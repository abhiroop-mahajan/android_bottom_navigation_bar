package com.example.firstgithubproject.nav_bar_directory.third_nav_bar

import android.content.Context
import android.support.v4.os.IResultReceiver._Parcel
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.firstgithubproject.R
import com.example.firstgithubproject.databinding.ActivityThirdNavBarBinding
import com.example.firstgithubproject.databinding.ItemThirdNavBarBinding
import com.example.firstgithubproject.fragments.*

class ThirdNavBarManager (listener:SaveData, context: Context,fragmentManager: FragmentManager,navBarBinding: ItemThirdNavBarBinding){

    interface SaveData{
        fun saveData(tab:Int)
    }

    private var listener:SaveData
    private var context:Context
    private var binding:ItemThirdNavBarBinding
    private var fragmentManager:FragmentManager

    private val tabListener=ArrayList<View>()
    private val fragmentList =ArrayList<Fragment>()
    private val selectedTabDrawables=ArrayList<Int>()
    private val unselectedTabDrawables = ArrayList<Int>()

    private var activeTab=1

    init {
        this.listener=listener
        this.context=context
        this.fragmentManager=fragmentManager
        this.binding = navBarBinding

        initialize()
    }

    private fun initialize() {
        initializeTabListeners()
        initializeFragmentsList()
        initializeSelectedTabDrawables()
        initializeUnselectedTabDrawables()
        clickListeners()
    }

    private fun initializeUnselectedTabDrawables() {
        unselectedTabDrawables.add(R.drawable.ic_unselected_home)
        unselectedTabDrawables.add(R.drawable.ic_unselected_search)
        unselectedTabDrawables.add(R.drawable.ic_unselected_analysis)
        unselectedTabDrawables.add(R.drawable.ic_unselected_history)
        unselectedTabDrawables.add(R.drawable.ic_unselected_profile)
    }

    private fun initializeSelectedTabDrawables() {
        selectedTabDrawables.add(R.drawable.ic_selected_home)
        selectedTabDrawables.add(R.drawable.ic_selected_search)
        selectedTabDrawables.add(R.drawable.ic_selected_analysis)
        selectedTabDrawables.add(R.drawable.ic_selected_histroy)
        selectedTabDrawables.add(R.drawable.ic_selected_profile)
    }

    private fun initializeFragmentsList() {
        fragmentList.add(FirstFragment())
        fragmentList.add(SecondFragment())
        fragmentList.add(ThirdFragment())
        fragmentList.add(FourthFragment())
        fragmentList.add(FifthFragment())
    }

    private fun clickListeners() {
        for (i in tabListener.indices)
            tabListener[i].setOnClickListener {
                updateNavBarStatus(i+1)
            }
    }

     fun updateNavBarStatus(tab: Int) {
        if(activeTab == tab)
            return

        if(tab==3)
            binding.vIndicator.visibility =View.GONE

        if(tab != 3)
            updateTab(tab-1,true)

        if(activeTab!=3)
            updateTab(activeTab-1,false)

        activeTab=tab
        listener.saveData(activeTab)
        navigateTab(activeTab-1)
    }

     private fun updateTab(tab: Int, isClicked: Boolean) {
        if(isClicked){
            (tabListener[tab] as AppCompatTextView).setCompoundDrawablesWithIntrinsicBounds(
                null,
                AppCompatResources.getDrawable(context,selectedTabDrawables[tab]),
                null,
                null
            )
            updateIndicator(tab)
            return
        }
        (tabListener[tab] as AppCompatTextView).setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(context,unselectedTabDrawables[tab]),
            null,
            null,
            null
        )
    }

    private fun updateIndicator(tab: Int) {
        binding.vIndicator.visibility =View.VISIBLE
        binding.vIndicator.updateLayoutParams<ConstraintLayout.LayoutParams> {
            startToStart = tabListener[tab].id
            endToEnd = tabListener[tab].id
            topToTop = tabListener[tab].id
            bottomToBottom = tabListener[tab].id
            verticalBias =0.8f
        }
    }
    private fun navigateTab(activeTab: Int) {
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragmentList[activeTab])
            commit()
        }
    }

    private fun initializeTabListeners() {
        binding.apply {
            tabListener.add(tabHome)
            tabListener.add(tabSearch)
            tabListener.add(tabAnalysis)
            tabListener.add(tabHistory)
            tabListener.add(tabProfile)
        }
    }
}