package com.example.testapplication.ui.main.adapter

import android.content.Context

interface MenuItemClickListener {
    fun onMenuItemClicked(item: Int, title: String)
    fun getContext(): Context
}