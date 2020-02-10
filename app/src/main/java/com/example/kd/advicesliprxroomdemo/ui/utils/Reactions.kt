package com.example.kd.advicesliprxroomdemo.ui.utils

import android.content.Context
import android.widget.Toast
import com.example.kd.advicesliprxroomdemo.utils.SingletonHolder

class Reactions private constructor(private val context: Context) {
    fun makeShortToast(message: String) {
        Toast.makeText(context.applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    companion object : SingletonHolder<Reactions, Context>(::Reactions)
}