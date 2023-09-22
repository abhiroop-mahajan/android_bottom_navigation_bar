package com.example.firstgithubproject.nav_bar_directory.sixth_nav_bar

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.firstgithubproject.R
import com.example.firstgithubproject.databinding.ItemSixthNavBarBinding
import com.example.firstgithubproject.fragments.FirstFragment
import com.example.firstgithubproject.fragments.FourthFragment
import com.example.firstgithubproject.fragments.SecondFragment
import com.example.firstgithubproject.fragments.ThirdFragment

class SixthNavBarManager(
    val context: Context,
    val fragmentManager: FragmentManager,
    val binding: ItemSixthNavBarBinding,
    val listener: SaveData
) {

    private var activeTab = 1

    interface SaveData {
        fun saveData(tab: Int)
    }

    private val fragmentList = ArrayList<Fragment>()
    private val selectedDrawableList = ArrayList<Int>()
    private val unselectedDrawableList = ArrayList<Int>()
    private val tabListenerList = ArrayList<AppCompatTextView>()

    init {
        initialiseFragmentList()
        initialiseSelectedDrawableList()
        initialiseUnselectedDrawableList()
        initialiseTabListenerList()
        setClickListeners()
    }

    private fun setClickListeners() {
        tabListenerList.apply {
            for (i in this.indices) {
                this[i].setOnClickListener {
                    updateTab(i + 1)
                }
            }
        }
    }

    fun updateTab(i: Int) {
        if (activeTab == i)
            return
        updateTab(activeTab - 1, false)
        updateTab(i - 1, true)
        activeTab = i
        listener.saveData(i)
        navigate(i - 1)
    }

    private fun updateTab(i: Int, isClicked: Boolean) {
        tabListenerList[i].apply {
            if (isClicked) {
                updateDrawable(i, this, true)
                updateBackground(i, this, true)
                return
            }
            updateDrawable(i, this, false)
            updateBackground(i, this, false)
        }
    }

    private fun updateBackground(i: Int, appCompatTextView: AppCompatTextView, isClicked: Boolean) {
        if (isClicked)
            appCompatTextView.setBackgroundResource(
                R.drawable.bg_bar_selected
            )
        else
        appCompatTextView.setBackgroundResource(android.R.color.transparent)
    }

    private fun updateDrawable(i: Int, appCompatTextView: AppCompatTextView, isClicked: Boolean) {
        if (isClicked)
            appCompatTextView.setCompoundDrawablesWithIntrinsicBounds(
                AppCompatResources.getDrawable(context, selectedDrawableList[i]),
                null,
                null,
                null
            )
        else
        appCompatTextView.setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(context, unselectedDrawableList[i]),
            null,
            null,
            null
        )

    }


    private fun navigate(i: Int) {
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragmentList[i])
            commit()
        }
    }

    private fun initialiseTabListenerList() {
        tabListenerList.apply {
            binding.apply {
                add(tabHome)
                add(tabSearch)
                add(tabAnalysis)
                add(tabHistory)
                add(tabProfile)
            }
        }
    }

    private fun initialiseUnselectedDrawableList() {
        unselectedDrawableList.apply {
            add(R.drawable.ic_unselected_home)
            add(R.drawable.ic_unselected_search)
            add(R.drawable.ic_unselected_analysis)
            add(R.drawable.ic_unselected_history)
            add(R.drawable.ic_unselected_profile)
        }
    }

    private fun initialiseSelectedDrawableList() {
        selectedDrawableList.apply {
            add(R.drawable.ic_selected_home)
            add(R.drawable.ic_selected_search)
            add(R.drawable.ic_selected_analysis)
            add(R.drawable.ic_selected_histroy)
            add(R.drawable.ic_selected_profile)
        }
    }

    private fun initialiseFragmentList() {
        fragmentList.apply {
            add(FirstFragment())
            add(SecondFragment())
            add(ThirdFragment())
            add(FourthFragment())
            add(FirstFragment())
        }
    }


}