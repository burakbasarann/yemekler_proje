package com.basaran.yemeklerproje.viewmodel

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basaran.yemeklerproje.entity.Promosyon
import com.basaran.yemeklerproje.entity.Yemekler
import com.basaran.yemeklerproje.entity.YemeklerFavoriDB
import com.basaran.yemeklerproje.repository.YemeklerDBRepository
import com.basaran.yemeklerproje.repository.YemeklerDaoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnaSayfaViewModel(application: Application) : AndroidViewModel(application) {
    var promosyonListesi = ArrayList<Promosyon>()
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    val yrepo = YemeklerDaoRepository()
    val dbrepo = YemeklerDBRepository(application)
    var yemeklerListesiFiyat: List<Yemekler>?
    var yemeklerFavoriKontrolListesi = MutableLiveData<List<YemeklerFavoriDB>>()

    init {
        favoriYemekKontrolYukle()
        promosyonResimYukle()
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
        promosyonListesi = yrepo.promosyonGetir()
        yemeklerListesiFiyat = yemeklerListesi.value

        yemeklerFavoriKontrolListesi = dbrepo.favoriKontrolleriGetir()
    }
    fun arama(aramaKelimesi: String){
        viewModelScope.launch(Dispatchers.Main) {
            yemeklerListesi.postValue(yrepo.aramaYemekler(aramaKelimesi))
        }
    }
   fun favoriYemekKontrolYukle(){
        dbrepo.favoriKontrolYemeklerr()
    }


    fun guncelle(favori_id:Int, favori_kontrol:Int){
        dbrepo.favoriGuncelle(favori_id, favori_kontrol)
    }

    fun kayitFavori(yemek_id: Int,yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: String) {
        dbrepo.yemekFavoriKayit(yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat)
    }


    fun yemeklerResimYukle(resimadi: String, imgResim: ImageView){
        yrepo.resimTutucu(resimadi, imgResim)
    }

    fun yemekleriYukle(){
        yrepo.tumYemekleriAl()
    }
    fun silFavori (yemek_id: Int){
        dbrepo.yemekFavoriSil(yemek_id)
    }

    fun promosyonResimYukle(){
        yrepo.promosyonResimleri()
    }
    fun kucuktenBuyuge(){
        yemeklerListesiFiyat = yemeklerListesi.value
        yemeklerListesiFiyat = yemeklerListesiFiyat?.sortedWith(compareBy { it.yemek_fiyat.toInt() })
        yemeklerListesi.value = yemeklerListesiFiyat
    }
    fun buyuktenKucuge(){
        yemeklerListesiFiyat = yemeklerListesi.value
        yemeklerListesiFiyat = yemeklerListesiFiyat?.sortedWith(compareBy { it.yemek_fiyat.toInt() })?.reversed()
        yemeklerListesi.value = yemeklerListesiFiyat
    }
    fun alfabeyeGoreSiralama(){
        yemeklerListesiFiyat = yemeklerListesi.value
        yemeklerListesiFiyat = yemeklerListesiFiyat?.sortedWith(compareBy { it.yemek_adi })
        yemeklerListesi.value = yemeklerListesiFiyat
    }
    fun alfabeyeGoreSiralamaTersi(){
        yemeklerListesiFiyat = yemeklerListesi.value
        yemeklerListesiFiyat = yemeklerListesiFiyat?.sortedWith(compareBy { it.yemek_adi })?.reversed()
        yemeklerListesi.value = yemeklerListesiFiyat
    }

}