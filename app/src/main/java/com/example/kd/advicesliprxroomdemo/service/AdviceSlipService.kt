package com.example.kd.advicesliprxroomdemo.service

import com.example.kd.advicesliprxroomdemo.model.AdviceSlip
import io.reactivex.Flowable
import retrofit2.http.GET

interface AdviceSlipService {
    @GET(AdviceSlipServiceConstants.ADVICE_PATH)
    fun getSlip(): Flowable<AdviceSlip>
}