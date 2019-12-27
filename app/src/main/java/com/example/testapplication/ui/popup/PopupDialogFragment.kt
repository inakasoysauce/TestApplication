package com.example.testapplication.ui.popup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.testapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.popup_fragment.*

class PopupDialogFragment: DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.popup_fragment, container, false)
        setBackground()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.get("name") as String
        val url = arguments?.get("url") as String
        Picasso.get()
            .load(url)
            .into(icon)
        city_name.text = name
    }

    private fun setBackground() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
    }
}