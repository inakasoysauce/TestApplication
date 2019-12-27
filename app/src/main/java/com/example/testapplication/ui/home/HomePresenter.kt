package com.example.testapplication.ui.home

import com.example.testapplication.R
import com.example.testapplication.base.BasePresenter
import com.example.testapplication.network.FirebaseInteractor

class HomePresenter(view: HomeView) : BasePresenter<HomeView>(view) {

    fun sendCode(code: String) {
        if (code.length == 4) {
            view?.showLoading()
            FirebaseInteractor.sendCode(code, {
                view?.hideLoading()
                view?.onSuccess()
            }, { message ->
                view?.hideLoading()
                if (message == null) {
                    view?.onError(view?.getContext()?.getString(R.string.error))
                } else {
                    view?.onError(message)
                }
            })
        }
    }
}