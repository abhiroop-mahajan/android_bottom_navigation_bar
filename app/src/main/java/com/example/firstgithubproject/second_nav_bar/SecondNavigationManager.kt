package com.example.firstgithubproject.second_nav_bar

import android.content.Context
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.firstgithubproject.R
import com.example.firstgithubproject.databinding.ItemFirstNavBarBinding
import com.example.firstgithubproject.databinding.ItemSecondNavBarBinding
import com.example.firstgithubproject.fragments.*

/**
 *  1 -> Home
 *  2 -> Search
 *  3 -> Analysis (FAB)
 *  4 -> History
 *  5 -> Profile
 */
class SecondNavigationManager(
    listener: DataSaver,
    context: Context,
    navBarBinder: ItemSecondNavBarBinding,
    fragmentManager: FragmentManager
) {
    private val navBarBinder: ItemSecondNavBarBinding
    private val context: Context
    private val fragmentManager: FragmentManager
    private val listener: DataSaver


    private val tabListener = ArrayList<View>()
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

        if (i != 3)
            updateTab(i - 1, true)

        if (activeTab != 3)
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
            (tabListener[i] as AppCompatTextView).apply {
                setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    AppCompatResources.getDrawable(context, selectedTabDrawables[i]),
                    null,
                    null
                )
                setTextColor(context.resources.getColor(R.color.selected_color, null))
            }
            return
        }
        (tabListener[i] as AppCompatTextView).apply {
            setCompoundDrawablesWithIntrinsicBounds(
                null,
                AppCompatResources.getDrawable(context, unselectedTabDrawables[i]),
                null,
                null
            )
            setTextColor(context.resources.getColor(R.color.black, null))
        }
    }

    private fun initialize() {
        if (fragmentArray.size == 0) {
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