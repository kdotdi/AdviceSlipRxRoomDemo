package com.example.kd.advicesliprxroomdemo.persistence

import com.example.kd.advicesliprxroomdemo.di.ApplicationModule
import com.example.kd.advicesliprxroomdemo.model.AdviceSlip
import com.example.kd.advicesliprxroomdemo.service.AdviceSlipService
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class AdviceSlipRepository @Inject constructor(
    @ApplicationModule.LocalDataSource private val adviceSlipDatabaseInstance: AdviceSlipDatabase,
    @ApplicationModule.RemoteDataSource private val adviceSlipServiceInstance: AdviceSlipService
) {
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
}