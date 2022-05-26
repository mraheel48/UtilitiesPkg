@file:Suppress("unused")

package com.example.utilitiespkg.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 *  Hide keyboard in fragment
 */
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

/**
 *  Hide keyboard in Activity
 */
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

/**
 *  Hide keyboard
 *
 *  @param view The view
 */
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.showKeyboard() {
    // Open keyboard
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}