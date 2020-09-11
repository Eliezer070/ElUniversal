/*
 *
 * ScreenManager.kt
 * CDS
 *
 * Created by eliezeralcazar on 11/5/19 3:46 PM.
 * Copyright © 2019 Coca-Cola Company. All rights reserved.
 */

package com.eas.sdk.commons.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.eas.sdk.R
import com.google.android.material.snackbar.Snackbar

object ScreenManager {


    private var dialog: AlertDialog? = null
    private var snackbar: Snackbar? = null

    fun showProgress(context: Context) {
            val inflater: LayoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: ViewGroup =
                inflater.inflate(R.layout.progress_layout, null, false) as ViewGroup
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            dialog = builder
                .setCancelable(false)
                .setView(view)
                .create()

            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.show()

    }

    fun hideProgress(){
        if(dialog != null)
            dialog!!.dismiss()
    }

    fun showSnackBar(root_layout: Activity, message: String) {
        snackbar = Snackbar.make(
            root_layout.getWindow().getDecorView().getRootView(),
            message,
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        snackbar!!.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar!!.view
        snackbarView.setBackgroundColor(Color.LTGRAY)
        val actionTextView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        actionTextView.setTextColor(Color.RED)
        actionTextView.textSize = 14f
        snackbar!!.show()
    }

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }

}