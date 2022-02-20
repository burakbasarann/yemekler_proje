package com.basaran.yemeklerproje.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable


@Entity(tableName = "gecmissiparisler")
data class YemeklerSiparisDB(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "siparis_id") @NotNull var siparis_id: Int,
                             @ColumnInfo(name = "sepet_yemek_id") @NotNull var sepet_yemek_id: Int,
                             @ColumnInfo(name = "yemek_adi") @NotNull var yemek_adi: String,
                             @ColumnInfo(name = "yemek_resim_adi") @NotNull var yemek_resim_adi : String,
                             @ColumnInfo(name = "yemek_fiyat") @NotNull var yemek_fiyat : String,
                             @ColumnInfo(name = "yemek_siparis_adet") @NotNull var yemek_siparis_adet : String,
                             @ColumnInfo(name = "kullanici_adi") @NotNull var kullanici_adi : String
) : Serializable {

}
