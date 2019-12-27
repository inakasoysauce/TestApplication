package com.example.testapplication.ui.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(private val cityItemClickListener: CityItemClickListener): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categories = ArrayList<Category>()
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        context = parent.context
        return CategoryViewHolder(itemView, context, cityItemClickListener)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.nameTextView.text = category.name
        holder.adapter?.addAll(category.cities)
    }

    fun addAll(list: List<Category>?) {
        list?.let {
            categories.addAll(it)
            notifyDataSetChanged()
        }
    }

    inner class CategoryViewHolder(itemView: View, context: Context?, cityItemClickListener: CityItemClickListener): RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.category_name
        private val cityListRecyclerView: RecyclerView = itemView.city_list
        var adapter: CityAdapter? = null
        init {
            adapter = CityAdapter(cityItemClickListener)
            cityListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            cityListRecyclerView.adapter = adapter
        }
    }
}