package com.example.kd.advicesliprxroomdemo.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AdviceSlip::class], version = 1)
abstract class AdviceSlipDatabase : RoomDatabase() {
    abstract fun adviceSlipDao(): AdviceSlipDao
}
