package com.basaran.yemeklerproje.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "favori")
data class YemeklerFavoriDB(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") @NotNull var id: Int,
                          @ColumnInfo(name = "favori") @NotNull var favori: Int
) : Serializable {

}
