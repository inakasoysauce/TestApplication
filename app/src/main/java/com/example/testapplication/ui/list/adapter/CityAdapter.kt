package com.example.testapplication.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.model.City
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.city_item.view.*

class CityAdapter(private val cityItemClickListener: CityItemClickListener) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private val cities = ArrayList<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item, parent, false)
        return CityViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        Picasso.get()
            .load(city.logo)
            .into(holder.logoImageView)
        holder.itemView.setOnClickListener {
            cityItemClickListener.cityItemClicked(city)
        }
    }

    fun addAll(list: List<City>?) {
        list?.let {
            cities.addAll(it)
            notifyDataSetChanged()
        }
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logoImageView: ImageView = itemView.city_logo
    }
}