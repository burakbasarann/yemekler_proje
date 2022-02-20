package com.basaran.yemeklerproje.viewmodel

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.basaran.yemeklerproje.entity.SepetYemekler
import com.basaran.yemeklerproje.repository.YemeklerDBRepository
import com.basaran.yemeklerproje.repository.YemeklerDaoRepository

class SepetViewModel(application: Application) : AndroidViewModel(application) {
    var sepetYemeklerListesi: MutableLiveData<List<SepetYemekler>>
    val yrepo = YemeklerDaoRepository()
    var sepetYemeklerListesiFiyat: ArrayList<SepetYemekler>?
    var dbrepo = YemeklerDBRepository(application)
    init {
        sepetYemeklerListesi = yrepo.sepetYemekleriGetir()
        sepetYemeklerListesiFiyat = sepetYemeklerListesi.value as ArrayList<SepetYemekler>?
    }


    fun sepetResimYukle(resimadi: String, imgResim: ImageView){
        yrepo.resimTutucu(resimadi, imgResim)
    }
    fun sepetYemekAl(kullanici_adi: String) {
        yrepo.sepettekileriGetir(kullanici_adi)
    }

    fun sepetUrunSil(sepet_yemek_id: Int, kullanici_adi: String) {
        yrepo.sepetUrunSil(sepet_yemek_id, kullanici_adi)
    }

    fun kayitSiparis(sepet_yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: String,yemek_siparis_adet: String, kullanici_adi: String) {
        dbrepo.yemekSiparisEkle(sepet_yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }



}