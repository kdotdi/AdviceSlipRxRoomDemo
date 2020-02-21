package com.example.kd.advicesliprxroomdemo

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.example.kd.advicesliprxroomdemo.di.DaggerApplicationComponent


open class AdviceSlipApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }
}