package com.example.kd.advicesliprxroomdemo.di

import android.content.Context
import android.webkit.URLUtil
import androidx.room.Room
import com.example.kd.advicesliprxroomdemo.persistence.AdviceSlipDatabase
import com.example.kd.advicesliprxroomdemo.persistence.AdviceSlipDatabaseConstants
import com.example.kd.advicesliprxroomdemo.service.AdviceSlipServiceConstants
import com.example.kd.advicesliprxroomdemo.service.AdviceSlipService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object ApplicationModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalDataSource

    @JvmStatic
    @Singleton
    @LocalDataSource
    @Provides
    fun provideDatabase(context: Context): AdviceSlipDatabase {
        return Room.databaseBuilder(context.applicationContext,
            AdviceSlipDatabase::class.java, AdviceSlipDatabaseConstants.DATABASE_NAME)
            .build()
    }

    @JvmStatic
    @Singleton
    @RemoteDataSource
    @Provides
    fun provideService(): AdviceSlipService {
        return if (URLUtil.isValidUrl(AdviceSlipServiceConstants.BASE_URL)) { Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AdviceSlipServiceConstants.BASE_URL)
            .build()
            .create(AdviceSlipService::class.java)
        } else throw IllegalArgumentException("Valid baseUrl is required")
    }
}
