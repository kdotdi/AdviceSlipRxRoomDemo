package com.example.kd.advicesliprxroomdemo.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kd.advicesliprxroomdemo.utils.SingletonHolder

@Database(entities = [AdviceSlip::class], version = 1)
abstract class AdviceSlipDatabase : RoomDatabase() {
    abstract fun adviceSlipDao(): AdviceSlipDao

    companion object : SingletonHolder<AdviceSlipDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
            AdviceSlipDatabase::class.java, "AdviceSlip.db")
            .build()
    })
}
