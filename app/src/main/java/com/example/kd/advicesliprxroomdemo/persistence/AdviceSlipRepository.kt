package com.example.kd.advicesliprxroomdemo.persistence

import android.content.Context
import com.example.kd.advicesliprxroomdemo.model.AdviceSlip
import com.example.kd.advicesliprxroomdemo.service.AdviceSlipServiceImplementation
import com.example.kd.advicesliprxroomdemo.utils.SingletonHolder
import io.reactivex.Completable
import io.reactivex.Flowable

class AdviceSlipRepository private constructor(context: Context) {
    private val adviceSlipServiceInstance =
        AdviceSlipServiceImplementation.instance
    private val adviceSlipDatabaseInstance =
        AdviceSlipDatabase.getInstance(context)

    fun getAdviceSlipFromApi(): Flowable<AdviceSlip> {
        return adviceSlipServiceInstance.getSlip()
    }

    fun getAdviceSlipsFromDb(): Flowable<List<com.example.kd.advicesliprxroomdemo.persistence.AdviceSlip>> {
        return adviceSlipDatabaseInstance.adviceSlipDao().getAll()
    }

    fun getMostRecentAdviceSlipFromDb(): Flowable<com.example.kd.advicesliprxroomdemo.persistence.AdviceSlip> {
        return adviceSlipDatabaseInstance.adviceSlipDao().getNewest()
    }

    fun storeAdviceSlipsInDb(adviceSlips: List<com.example.kd.advicesliprxroomdemo.persistence.AdviceSlip>): Completable {
        return adviceSlipDatabaseInstance.adviceSlipDao().insertAll(adviceSlips)
    }

    companion object : SingletonHolder<AdviceSlipRepository, Context>(::AdviceSlipRepository)
}