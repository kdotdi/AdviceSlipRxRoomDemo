package com.example.kd.advicesliprxroomdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kd.advicesliprxroomdemo.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.context?.let {
            val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

            val uppercaseAdviceObserver = Observer<String> { advice ->
                transformedAdvice.text = advice
            }
            val tickingAdviceObserver = Observer<String> { advice ->
                tickingAdvice.text = advice
            }
            val persistedAdviceObserver = Observer<String?> { advice ->
                persistedAdvice.text = advice
            }

            viewModel.transformedAdvice.observe(viewLifecycleOwner, uppercaseAdviceObserver)
            viewModel.tickingAdvice.observe(viewLifecycleOwner, tickingAdviceObserver)
            viewModel.persistedAdvice.observe(viewLifecycleOwner, persistedAdviceObserver)

            captureAdvice.setOnClickListener { viewModel.persistAdviceSlip(transformedAdvice.text.toString()) }
        }
    }
}
