package com.example.kd.advicesliprxroomdemo.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface AdviceSlipDao {
    @Query("SELECT * FROM AdviceSlip")
    fun getAll(): Flowable<List<AdviceSlip>>

    @Query("SELECT * FROM AdviceSlip ORDER BY id DESC LIMIT 1")
    fun getNewest(): Flowable<AdviceSlip>

    @Query("SELECT * FROM AdviceSlip WHERE id IN (:adviceSlipIds)")
    fun loadAllByIds(adviceSlipIds: IntArray): Flowable<List<AdviceSlip>>

    @Insert
    fun insertAll(adviceSlips: List<AdviceSlip>): Completable

    @Delete
    fun delete(user: AdviceSlip)

}