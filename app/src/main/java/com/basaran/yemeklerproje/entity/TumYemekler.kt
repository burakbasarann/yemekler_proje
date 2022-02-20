package com.basaran.yemeklerproje.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TumYemekler(
    @SerializedName("yemekler")
    @Expose var yemekler: List<Yemekler>,
    @SerializedName("success")
    @Expose var success: Int
) {
}