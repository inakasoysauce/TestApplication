package com.example.testapplication.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.testapplication.base.BaseView

interface MainView : BaseView {
    fun goToScreen(fragment: Fragment)
    fun getContext(): Context
}