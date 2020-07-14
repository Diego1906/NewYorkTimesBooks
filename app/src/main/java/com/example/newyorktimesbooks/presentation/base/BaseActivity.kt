package com.example.newyorktimesbooks.presentation.base

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.include_toolbar.*

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(@StringRes resId: Int, showBackUpButton: Boolean = false) {
        toolbarMain.title = getString(resId)
        setSupportActionBar(toolbarMain)
        if (showBackUpButton)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}