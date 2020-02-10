package com.example.kd.advicesliprxroomdemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.kd.advicesliprxroomdemo.model.AdviceSlip
import com.example.kd.advicesliprxroomdemo.persistence.AdviceSlipRepository
import com.example.kd.advicesliprxroomdemo.service.AdviceSlipConstants
import com.example.kd.advicesliprxroomdemo.ui.utils.Reactions
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel(private val adviceSlipRepository: AdviceSlipRepository, private val reactions: Reactions) : ViewModel() {
    val persistedAdvice by lazy {
        setUpPersistedAdviceLiveData()
    }
    val transformedAdvice by lazy {
        setUpTransformedAdviceLiveData()
    }
    val tickingAdvice by lazy {
        setUpTickingAdviceLiveData()
    }

    private val disposables = CompositeDisposable()

    private fun setUpPersistedAdviceLiveData() : LiveData<String?> {
        return LiveDataReactiveStreams.fromPublisher(adviceSlipRepository.getMostRecentAdviceSlipFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.advice
            }
            .onErrorReturnItem("Error consuming stream")
        )
    }

    private fun setUpTransformedAdviceLiveData() : LiveData<String> {
        return LiveDataReactiveStreams.fromPublisher(adviceSlipRepository.getAdviceSlipFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                getAdviceAndTransform(it)
            }
            .onErrorReturnItem("Error consuming stream")
        )
    }

    private fun setUpTickingAdviceLiveData() : LiveData<String> {
        return LiveDataReactiveStreams.fromPublisher(Flowable.interval(AdviceSlipConstants.TICKING_INTERVAL_IN_SECONDS, TimeUnit.SECONDS)
            .flatMap { adviceSlipRepository.getAdviceSlipFromApi() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.slip.advice
            }
            .onErrorReturnItem("Error consuming stream")
        )
    }

    private fun getAdviceAndTransform(adviceSlip: AdviceSlip) : String {
        return adviceSlip.slip.advice.toUpperCase()
    }

    fun persistAdviceSlip(advice: String) {
        disposables.add(
            adviceSlipRepository.storeAdviceSlipsInDb(
                    arrayListOf(com.example.kd.advicesliprxroomdemo.persistence.AdviceSlip(0, 0, advice)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    reactions.makeShortToast("Advice persisted")
                }, {
                    reactions.makeShortToast("Failed to persist Advice: ${it.message}")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}
