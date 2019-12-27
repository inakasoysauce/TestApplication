package com.example.testapplication.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.util.Constants
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    private var language = Constants.currentLanguage
    private var listener: LanguageSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as LanguageSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (language == Constants.ENGLISH) {
            eng.isChecked = true
            current.text = getString(R.string.english)
        } else {
            hun.isChecked = true
            current.text = getString(R.string.magyar)
        }
        btn_save.isEnabled = false
        eng.setOnClickListener {
            btn_save.isEnabled = language != Constants.ENGLISH
        }
        hun.setOnClickListener {
            btn_save.isEnabled = language != Constants.HUNGARIAN
        }

        btn_save.setOnClickListener {
            language = if (eng.isChecked) {
                Constants.ENGLISH
            } else {
                Constants.HUNGARIAN
            }
            listener?.languageSelected(language)
        }
    }
}