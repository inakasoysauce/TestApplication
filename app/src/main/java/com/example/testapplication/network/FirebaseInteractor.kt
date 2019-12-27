package com.example.testapplication.network

import com.example.testapplication.model.Category
import com.example.testapplication.model.City
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object FirebaseInteractor {

    private val database = FirebaseDatabase.getInstance()

    fun sendCode(code: String, success: () -> Unit, error: (String?) -> Unit) {
        val currentTime = System.currentTimeMillis() / 1000
        val ref = database.getReference("codes").child(currentTime.toString())
        ref.setValue(code).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                success()
            } else {
                error(task.exception?.localizedMessage)
            }
        }
    }

    fun loadCategories(success: (ArrayList<Category>) -> Unit, error: (String?) -> Unit) {
        val categories = database.getReference("lists").orderByKey()
        val categoryList = ArrayList<Category>()
        categories.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snap: DataSnapshot) {
                snap.children.forEach { item ->
                    val category = Category()
                    category.name = item.child("name").value as String
                    getCitiesByCategory(category, item)
                    categoryList.add(category)
                }
                success(categoryList)
            }

            override fun onCancelled(error: DatabaseError) {
                error(error.message)
            }
        })
    }

    private fun getCitiesByCategory(category: Category, item: DataSnapshot) {
        val cities = ArrayList<City>()
        item.children.forEach { cityItem ->
            if (cityItem.hasChildren()) {
                val city = City()
                city.name = cityItem.child("name").value as String
                city.icon = cityItem.child("icon").value as String
                city.logo = cityItem.child("logo").value as String
                cities.add(city)
            }
        }
        category.cities.addAll(cities)
    }
}