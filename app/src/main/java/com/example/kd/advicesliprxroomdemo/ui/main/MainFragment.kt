package com.example.kd.advicesliprxroomdemo.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kd.advicesliprxroomdemo.R
import com.example.kd.advicesliprxroomdemo.persistence.AdviceSlipRepository
import com.example.kd.advicesliprxroomdemo.ui.utils.Reactions
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.context?.let {
            val adviceSlipRepository = AdviceSlipRepository.getInstance(it)
            val reactions = Reactions.getInstance(it)

            viewModel = ViewModelProvider(this, MainViewModelFactory(adviceSlipRepository, reactions)).get(
                MainViewModel::class.java
            )

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
