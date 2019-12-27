package com.example.testapplication.base

interface BaseView {

    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String?)
}