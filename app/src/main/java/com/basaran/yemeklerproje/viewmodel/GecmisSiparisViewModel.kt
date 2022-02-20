package com.basaran.yemeklerproje.viewmodel

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.basaran.yemeklerproje.entity.YemeklerSiparisDB
import com.basaran.yemeklerproje.repository.YemeklerDBRepository
import com.basaran.yemeklerproje.repository.YemeklerDaoRepository

class GecmisSiparisViewModel(application: Application) : ViewModel()  {
    val yrepo = YemeklerDaoRepository()
    val dbrepo = YemeklerDBRepository(application)
    var siparislerListesi = MutableLiveData<List<YemeklerSiparisDB>>()
    init {
        siparisYemekleriYukle()
        siparislerListesi = dbrepo.siparisleriGetir()
    }

    fun siparisYemekleriYukle(){
        dbrepo.tumSiparisleriAl()
    }
    fun siparisResimYukle(resimadi: String, imgResim: ImageView){
        yrepo.resimTutucu(resimadi, imgResim)
    }
    fun silSiparis (siparis_id: Int, sepet_yemek_id: Int){
        dbrepo.yemekSiparisSil(siparis_id,sepet_yemek_id)
    }
}