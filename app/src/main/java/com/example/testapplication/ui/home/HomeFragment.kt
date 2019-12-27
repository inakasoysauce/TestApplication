package com.example.testapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.base.BaseView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeView {

    private var presenter: HomePresenter? = null
    private var listener: BaseView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = HomePresenter(this)
        listener = context as BaseView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_send.setOnClickListener {
            presenter?.sendCode(pin_view.text.toString())
        }
    }

    override fun showLoading() {
        listener?.showLoading()
    }

    override fun hideLoading() {
        listener?.hideLoading()
    }

    override fun showMessage(message: String?) {
        listener?.showMessage(message)
    }

    override fun onSuccess() {
        showMessage(getString(R.string.code_sent))
    }

    override fun onError(message: String?) {
        showMessage(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroyView()
    }
}