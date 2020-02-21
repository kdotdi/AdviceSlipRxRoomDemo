package com.example.kd.advicesliprxroomdemo.di

import android.content.Context
import com.example.kd.advicesliprxroomdemo.AdviceSlipApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelBuilder::class,
        MainViewModelModule::class,
        ApplicationModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<AdviceSlipApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
