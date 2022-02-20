package com.basaran.yemeklerproje.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.basaran.yemeklerproje.entity.YemeklerSiparisDB

@Dao
interface YemeklerSiparisDao {
    @Query("SELECT * FROM gecmissiparisler")
    suspend fun tumSiparisler() : List<YemeklerSiparisDB>

    @Insert
    suspend fun yemekSiparisEkle(yemekSiparis : YemeklerSiparisDB)

    @Delete
    suspend fun yemekSiparisSil(yemekSiparis : YemeklerSiparisDB)

}