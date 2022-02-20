package com.basaran.yemeklerproje.room

import androidx.room.*
import com.basaran.yemeklerproje.entity.YemeklerFavoriDB

@Dao
interface YemeklerFavoriDao {
    @Query("SELECT * FROM favori")
    suspend fun tumYemeklerFavori() : List<YemeklerFavoriDB>

    @Update
    suspend fun yemeklerFavoriGuncelle(yemeklerFavori : YemeklerFavoriDB)

}