package com.example.kd.advicesliprxroomdemo.di

import androidx.lifecycle.ViewModel
import com.example.kd.advicesliprxroomdemo.ui.main.MainFragment
import com.example.kd.advicesliprxroomdemo.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun MainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindViewModel(viewmodel: MainViewModel): ViewModel
}
