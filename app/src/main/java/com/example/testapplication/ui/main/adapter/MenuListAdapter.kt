package com.example.testapplication.ui.main.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.util.Constants
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.menu_list_item.view.*

class MenuListAdapter(private val menuItemClickListener: MenuItemClickListener) :
    RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder>() {
    var selectedItem: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_list_item, parent, false)
        return MenuListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return menuItemClickListener.getContext().resources.getStringArray(R.array.menu_list).size
    }

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
        val item =
            menuItemClickListener.getContext().resources.getStringArray(R.array.menu_list)[position]
        holder.titleTextView.text = item
        checkSelected(position, holder)
        holder.itemView.setOnClickListener {
            menuItemClickListener.onMenuItemClicked(position, item)
            itemSelected(position)
        }
    }

    fun showList() {
        notifyDataSetChanged()
    }

    private fun itemSelected(item: Int) {
        selectedItem = item
        notifyDataSetChanged()
    }

    private fun checkSelected(item: Int, holder: MenuListViewHolder) {
        if (selectedItem == item) {
            holder.titleTextView.setTypeface(holder.titleTextView.typeface, Typeface.BOLD)
        } else {
            holder.titleTextView.setTypeface(null, Typeface.NORMAL)
        }
    }

    class MenuListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.title
    }
}