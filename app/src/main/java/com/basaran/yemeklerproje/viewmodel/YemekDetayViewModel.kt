package com.basaran.yemeklerproje.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.basaran.yemeklerproje.repository.YemeklerDaoRepository

class YemekDetayViewModel() : ViewModel() {
    val yrepo = YemeklerDaoRepository()


    fun detayResimYukle(resimadi: String, imgResim: ImageView){
        yrepo.resimTutucu(resimadi, imgResim)
    }

    fun kayit(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String){
        yrepo.sepeteKayit(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

}