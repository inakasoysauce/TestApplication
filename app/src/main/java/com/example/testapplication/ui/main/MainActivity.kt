package com.example.testapplication.ui.main

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.ui.main.adapter.MenuItemClickListener
import com.example.testapplication.ui.main.adapter.MenuListAdapter
import com.example.testapplication.ui.settings.LanguageSelectedListener
import com.example.testapplication.util.Constants
import com.example.testapplication.util.setTextWidthFade
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MainView, MenuItemClickListener,
    LanguageSelectedListener {

    private var presenter: MainPresenter? = null
    private var adapter: MenuListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkLanguage()
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        initActionBar()
        initNavigationView()
        checkIfShowSettings()
    }

    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initNavigationView() {
        adapter = MenuListAdapter(this)
        menu_list.adapter = adapter
        adapter?.showList()
    }

    override fun onMenuItemClicked(item: Int, title: String) {
        closeDrawer()
        if (item != adapter?.selectedItem) {
            tv_title.setTextWidthFade(title, 300L)
            setScreen(item)
        }
    }

    private fun setScreen(item: Int) {
        presenter?.createScreen(item)
        adapter?.selectedItem = item
    }

    override fun goToScreen(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun showLoading() {
        loading_screen.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_screen.visibility = View.GONE
    }

    override fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        ev?.let {
            if (it.action == MotionEvent.ACTION_DOWN) {
                val v = currentFocus
                if (v is EditText) {
                    val outRect = Rect()
                    v.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(it.rawX.toInt(), it.rawY.toInt())) {
                        v.clearFocus()
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v.windowToken, 0)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun checkIfShowSettings() {
        val screen = if (Constants.showSettings) {
            Constants.showSettings = false
            2
        } else {
            0
        }
        tv_title.text = resources.getStringArray(R.array.menu_list)[screen]
        setScreen(screen)
    }

    override fun languageSelected(code: String) {
        setAppLocale(code)
        Constants.showSettings = true
        recreate()
    }

    private fun checkLanguage() {
        val code = Prefs.getString(Constants.LANGUAGE_CODE, getString(R.string.lang_code))
        setAppLocale(code)
    }

    private fun setAppLocale(code: String) {
        Constants.currentLanguage = code
        Prefs.putString(Constants.LANGUAGE_CODE, code)
        val dm = resources.displayMetrics
        val config = resources.configuration
        config.setLocale(Locale(code))
        resources.updateConfiguration(config, dm)
    }

    override fun getContext(): Context {
        return this
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroyView()
    }
}
