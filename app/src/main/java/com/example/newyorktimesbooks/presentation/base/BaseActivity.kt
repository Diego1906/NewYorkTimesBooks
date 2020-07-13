package com.example.newyorktimesbooks.presentation.base

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, @StringRes resId: Int) {
        toolbar.title = getString(resId)
        setSupportActionBar(toolbar)
    }
}