package com.example.kd.advicesliprxroomdemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kd.advicesliprxroomdemo.persistence.AdviceSlipRepository
import com.example.kd.advicesliprxroomdemo.ui.utils.Reactions

class MainViewModelFactory(private val adviceSlipRepository: AdviceSlipRepository, private val reactions: Reactions) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(adviceSlipRepository, reactions) as T
    }
}