package com.basaran.yemeklerproje.viewmodel

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.basaran.yemeklerproje.entity.YemeklerFavoriDB
import com.basaran.yemeklerproje.entity.YemeklerRoomDB
import com.basaran.yemeklerproje.repository.YemeklerDBRepository
import com.basaran.yemeklerproje.repository.YemeklerDaoRepository

class FavorilerViewModel(application: Application) : ViewModel() {


    val dbrepo = YemeklerDBRepository(application)
    val yrepo = YemeklerDaoRepository()
    var yemeklerFavoriListesi = MutableLiveData<List<YemeklerRoomDB>>()
    var yemeklerFavoriKontrolListesi = MutableLiveData<List<YemeklerFavoriDB>>()

    init {
        favoriYemekKontrolYukle()
        yemeklerFavoriKontrolListesi = dbrepo.favoriKontrolleriGetir()
        favoriYemekleriYukle()
        yemeklerFavoriListesi = dbrepo.favorileriGetir()
    }
    fun favoriYemekKontrolYukle(){
        dbrepo.favoriKontrolYemeklerr()
    }

    fun favoriYemekleriYukle(){
        dbrepo.tumFavoriYemekleriAl()
    }
    fun favoriResimYukle(resimadi: String, imgResim: ImageView){
        yrepo.resimTutucu(resimadi, imgResim)
    }

    fun guncelle(favori_id:Int, favori_kontrol:Int){
        dbrepo.favoriGuncelle(favori_id, favori_kontrol)
    }
    fun silFavori (yemek_id: Int){
        dbrepo.yemekFavoriSil(yemek_id)
    }
}