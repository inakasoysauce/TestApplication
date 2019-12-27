package com.example.testapplication.ui.main

import com.example.testapplication.base.BasePresenter
import com.example.testapplication.ui.home.HomeFragment
import com.example.testapplication.ui.list.ListFragment
import com.example.testapplication.ui.settings.SettingsFragment

class MainPresenter(view: MainView) : BasePresenter<MainView>(view) {

    fun createScreen(item: Int) {
        when (item) {
            0 -> createHomeFragment()
            1 -> createListFragment()
            2 -> createSettingsFragment()
        }
    }

    private fun createHomeFragment() {
        val fragment = HomeFragment()
        view?.goToScreen(fragment)
    }

    private fun createListFragment() {
        val fragment = ListFragment()
        view?.goToScreen(fragment)
    }

    private fun createSettingsFragment() {
        val fragment = SettingsFragment()
        view?.goToScreen(fragment)
    }
}