package com.example.testapplication.base

abstract class BasePresenter<V>(protected var view: V?) {

    open fun destroyView() {
        view = null
    }
}