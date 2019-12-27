package com.example.testapplication.ui.home

import android.content.Context
import com.example.testapplication.base.BaseView

interface HomeView: BaseView {

    fun onSuccess()
    fun onError(message: String?)
    fun getContext(): Context?
}