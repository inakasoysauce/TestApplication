package com.example.testapplication.ui.list

import com.example.testapplication.base.BasePresenter
import com.example.testapplication.network.FirebaseInteractor

class ListPresenter(view: ListView): BasePresenter<ListView>(view) {

    fun getCategories() {
        view?.showLoading()
        FirebaseInteractor.loadCategories({
            view?.hideLoading()
            view?.showCategories(it)

        },{message ->
            view?.hideLoading()
            view?.showMessage(message)
        })
    }
}