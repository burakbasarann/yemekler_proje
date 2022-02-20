package com.basaran.yemeklerproje.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TumSepetYemekler(@SerializedName("sepet_yemekler")
                            @Expose var sepet_yemekler: List<SepetYemekler>,
                            @SerializedName("success")
                            @Expose var success: Int
)
