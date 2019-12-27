package com.example.testapplication.ui.list

import com.example.testapplication.base.BaseView
import com.example.testapplication.model.Category

interface ListView : BaseView {

    fun showCategories(categories: List<Category>?)
}