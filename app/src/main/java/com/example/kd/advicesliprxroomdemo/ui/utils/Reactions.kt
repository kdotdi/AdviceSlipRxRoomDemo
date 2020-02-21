package com.example.kd.advicesliprxroomdemo.ui.utils

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class Reactions @Inject constructor(private val context: Context) {
    fun makeShortToast(message: String) {
        Toast.makeText(context.applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}