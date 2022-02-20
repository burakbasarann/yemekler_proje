package com.basaran.yemeklerproje.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.basaran.yemeklerproje.entity.YemeklerSiparisDB


@Database(entities = [YemeklerSiparisDB::class], version = 1)
abstract class VeritabaniSiparisler : RoomDatabase() {
    abstract fun yemeklerSiparisDao() : YemeklerSiparisDao

    companion object{
        var INSTANCE : VeritabaniSiparisler? = null

        fun veritabaniSiparisErisim(context: Context) : VeritabaniSiparisler?{
            if (INSTANCE == null){
                synchronized(VeritabaniSiparisler::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        VeritabaniSiparisler::class.java,
                        "gecmissiparisler.sqlite")
                        .createFromAsset("gecmissiparisler.sqlite")
                        .build()
                }
            }
            return  INSTANCE
        }
    }
}