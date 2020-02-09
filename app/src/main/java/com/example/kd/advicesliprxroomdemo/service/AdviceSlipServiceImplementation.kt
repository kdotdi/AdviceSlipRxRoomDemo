package com.example.kd.advicesliprxroomdemo.service

import android.webkit.URLUtil
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface AdviceSlipServiceImplementation {
    companion object {
        val instance: AdviceSlipService by lazy {
            if (URLUtil.isValidUrl(AdviceSlipConstants.BASE_URL)) {
                val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AdviceSlipConstants.BASE_URL)
                    .build()
                retrofit.create(AdviceSlipService::class.java)
            } else throw IllegalArgumentException("Valid baseUrl is required")
        }
    }
}
