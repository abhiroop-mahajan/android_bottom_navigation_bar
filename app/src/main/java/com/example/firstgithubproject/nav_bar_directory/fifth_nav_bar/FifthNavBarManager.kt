package com.example.firstgithubproject.nav_bar_directory.fifth_nav_bar

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.firstgithubproject.R
import com.example.firstgithubproject.databinding.ItemFifthNavBarBinding
import com.example.firstgithubproject.databinding.ItemFirstNavBarBinding
import com.example.firstgithubproject.fragments.*

class FifthNavBarManager(
    listener: DataSaver,
    context: Context,
    navBarBinder: ItemFifthNavBarBinding,
    fragmentManager: FragmentManager
) {
    private val navBarBinder: ItemFifthNavBarBinding
    private val context: Context
    private val fragmentManager: FragmentManager
    private val listener: DataSaver

    private val tabListener = ArrayList<AppCompatTextView>()
    private val fragmentArray = ArrayList<Fragment>()
    private val selectedTabDrawables = ArrayList<Int>()
    private val unselectedTabDrawables = ArrayList<Int>()
    private var activeTab = 1

    init {
        this.context = context
        this.navBarBinder = navBarBinder
        this.fragmentManager = fragmentManager
        this.listener = listener
        initialize()
    }


    private fun initUnselectedTabDrawables() {
        unselectedTabDrawables.add(R.drawable.ic_unselected_home)
        unselectedTabDrawables.add(R.drawable.ic_unselected_search)
        unselectedTabDrawables.add(R.drawable.ic_unselected_analysis)
        unselectedTabDrawables.add(R.drawable.ic_unselected_history)
        unselectedTabDrawables.add(R.drawable.ic_unselected_profile)
    }

    private fun initSelectedTabDrawables() {
        selectedTabDrawables.add(R.drawable.ic_selected_home)
        selectedTabDrawables.add(R.drawable.ic_selected_search)
        selectedTabDrawables.add(R.drawable.ic_selected_analysis)
        selectedTabDrawables.add(R.drawable.ic_selected_histroy)
        selectedTabDrawables.add(R.drawable.ic_selected_profile)
    }

    private fun initClickListeners() {
        for (i in tabListener.indices) {
            tabListener[i].setOnClickListener {
                updateNavBar(i + 1)
            }
        }
    }

    fun updateNavBar(i: Int) {
        if (i == activeTab)
            return
        updateTab(i - 1, true)
        updateTab(activeTab - 1, false)
        activeTab = i
        listener.saveData(activeTab)
        navigateToTabScreen(activeTab - 1)
    }

    private fun navigateToTabScreen(i: Int) {
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragmentArray[i])
            commit()
        }
    }

    private fun updateTab(i: Int, isClicked: Boolean) {
        if (isClicked) {
            tabListener[i].setCompoundDrawablesWithIntrinsicBounds(
                null,
                AppCompatResources.getDrawable(context, selectedTabDrawables[i]),
                null,
                null
            )
            tabListener[i].setTextColor(context.resources.getColor(R.color.selected_color, null))
            updateIndicator(i)
            return
        }
        tabListener[i].setCompoundDrawablesWithIntrinsicBounds(
            null,
            AppCompatResources.getDrawable(context, unselectedTabDrawables[i]),
            null,
            null
        )
        tabListener[i].setTextColor(context.resources.getColor(R.color.black, null))
    }

    private fun updateIndicator(i: Int) {
        navBarBinder.viewIndicator.updateLayoutParams<ConstraintLayout.LayoutParams> {
            startToStart = tabListener[i].id
            endToEnd = tabListener[i].id
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            bottomToTop = ConstraintLayout.LayoutParams.PARENT_ID
        }
    }

    private fun initialize() {
        if(fragmentArray.size ==0) {
            initFragmentArray()
            initTabClickListener()
            initClickListeners()
            initSelectedTabDrawables()
            initUnselectedTabDrawables()
        }
    }


    private fun initTabClickListener() {
        navBarBinder.apply {
            tabListener.add(tabHome)
            tabListener.add(tabSearch)
            tabListener.add(tabAnalysis)
            tabListener.add(tabHistory)
            tabListener.add(tabProfile)
        }
    }

    private fun initFragmentArray() {
        fragmentArray.add(FirstFragment())
        fragmentArray.add(SecondFragment())
        fragmentArray.add(ThirdFragment())
        fragmentArray.add(FourthFragment())
        fragmentArray.add(FifthFragment())

    }

    interface DataSaver {
        fun saveData(tab: Int)
    }
}