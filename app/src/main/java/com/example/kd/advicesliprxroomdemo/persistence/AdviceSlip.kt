package com.example.kd.advicesliprxroomdemo.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AdviceSlip(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "slip_id") val slip_id: Int?,
    @ColumnInfo(name = "advice") val advice: String?
)
