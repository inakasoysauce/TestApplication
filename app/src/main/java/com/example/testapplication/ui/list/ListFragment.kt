package com.example.testapplication.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.base.BaseView
import com.example.testapplication.model.Category
import com.example.testapplication.model.City
import com.example.testapplication.ui.list.adapter.CategoryAdapter
import com.example.testapplication.ui.list.adapter.CityItemClickListener
import com.example.testapplication.ui.popup.PopupDialogFragment
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment: Fragment(), ListView, CityItemClickListener {

    private var listener: BaseView? = null
    private var presenter: ListPresenter? = null
    private var adapter: CategoryAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as BaseView
        presenter = ListPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        presenter?.getCategories()
    }

    private fun initRecyclerView() {
        adapter = CategoryAdapter(this)
        category_list.adapter = adapter
    }

    override fun showCategories(categories: List<Category>?) {
        adapter?.addAll(categories)
    }

    override fun cityItemClicked(city: City) {
        val bundle = Bundle()
        bundle.putString("url",city.icon)
        bundle.putString("name", city.name)
        val popupDialogFragment = PopupDialogFragment()
        popupDialogFragment.arguments = bundle
        popupDialogFragment.show(activity?.supportFragmentManager!!, "popup")
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

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroyView()
    }
}