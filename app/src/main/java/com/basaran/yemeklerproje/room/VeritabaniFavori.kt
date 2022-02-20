package com.basaran.yemeklerproje.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.basaran.yemeklerproje.entity.YemeklerFavoriDB


@Database(entities = [YemeklerFavoriDB::class], version = 1)
abstract class VeritabaniFavori : RoomDatabase() {
    abstract fun yemeklerFavoriDao() : YemeklerFavoriDao

    companion object{
        var INSTANCE : VeritabaniFavori? = null

        fun veritabaniFavoriErisim(context: Context) : VeritabaniFavori?{
            if (INSTANCE == null){
                synchronized(VeritabaniFavori::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        VeritabaniFavori::class.java,
                        "favori.sqlite")
                        .createFromAsset("favori.sqlite")
                        .build()
                }
            }
            return  INSTANCE
        }
    }
}