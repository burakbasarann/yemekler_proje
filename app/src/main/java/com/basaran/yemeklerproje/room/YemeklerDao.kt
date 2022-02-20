package com.basaran.yemeklerproje.room

import androidx.room.*
import com.basaran.yemeklerproje.entity.YemeklerRoomDB

@Dao
interface YemeklerDao {
    @Query("SELECT * FROM yemekler")
    suspend fun tumYemeklerFavori() : List<YemeklerRoomDB>

    @Insert
    suspend fun yemekFavoriEkle(yemekFavori : YemeklerRoomDB)

    @Delete
    suspend fun yemekFavoriSil(yemekFavori : YemeklerRoomDB)

}