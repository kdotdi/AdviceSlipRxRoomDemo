package com.example.kd.advicesliprxroomdemo.model

data class AdviceSlip(
    val slip: Slip
) {
    data class Slip(
        var advice: String,
        var slip_id: String
    )
}