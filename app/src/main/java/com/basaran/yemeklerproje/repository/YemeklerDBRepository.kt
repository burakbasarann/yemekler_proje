package com.basaran.yemeklerproje.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.basaran.yemeklerproje.entity.YemeklerFavoriDB
import com.basaran.yemeklerproje.entity.YemeklerRoomDB
import com.basaran.yemeklerproje.entity.YemeklerSiparisDB
import com.basaran.yemeklerproje.room.Veritabani
import com.basaran.yemeklerproje.room.VeritabaniFavori
import com.basaran.yemeklerproje.room.VeritabaniSiparisler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YemeklerDBRepository(var application: Application) {
    var vt: Veritabani
    var favoriYemeklerListesi: MutableLiveData<List<YemeklerRoomDB>>
    var vt2: VeritabaniFavori
    var favoriKontrolListesi: MutableLiveData<List<YemeklerFavoriDB>>
    var vt3: VeritabaniSiparisler
    var siparislerListesi: MutableLiveData<List<YemeklerSiparisDB>>

    init {
        vt2 = VeritabaniFavori.veritabaniFavoriErisim(application)!!
        vt = Veritabani.veritabaniErisim(application)!!
        vt3 = VeritabaniSiparisler.veritabaniSiparisErisim(application)!!
        siparislerListesi = MutableLiveData()
        favoriYemeklerListesi = MutableLiveData()
        favoriKontrolListesi = MutableLiveData()
    }

    fun siparisleriGetir(): MutableLiveData<List<YemeklerSiparisDB>> {
        return siparislerListesi
    }
    fun tumSiparisleriAl() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            siparislerListesi.value = vt3.yemeklerSiparisDao().tumSiparisler()
        }
    }
    fun yemekSiparisEkle(sepet_yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: String,yemek_siparis_adet: String, kullanici_adi: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniSiparis = YemeklerSiparisDB(0, sepet_yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat,yemek_siparis_adet, kullanici_adi)
            vt3.yemeklerSiparisDao().yemekSiparisEkle(yeniSiparis)
        }
    }

    fun yemekSiparisSil(siparis_id: Int,sepet_yemek_id: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinecekSiparis = YemeklerSiparisDB(siparis_id, sepet_yemek_id,"","","","","")
            vt3.yemeklerSiparisDao().yemekSiparisSil(silinecekSiparis)
            tumSiparisleriAl()
        }
    }



    fun yemekFavoriKayit(yemek_id: Int,yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val yeniFavoriYemek = YemeklerRoomDB(yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat)
            vt.yemeklerDao().yemekFavoriEkle(yeniFavoriYemek)
        }
    }

    fun yemekFavoriSil(kisi_id: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinecekFavoriYemek = YemeklerRoomDB(kisi_id,"","","")
            vt.yemeklerDao().yemekFavoriSil(silinecekFavoriYemek)
            tumFavoriYemekleriAl()
        }
    }
    fun favorileriGetir(): MutableLiveData<List<YemeklerRoomDB>> {
        return favoriYemeklerListesi
    }
    fun tumFavoriYemekleriAl() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            favoriYemeklerListesi.value = vt.yemeklerDao().tumYemeklerFavori()
        }
    }
    fun favoriKontrolleriGetir(): MutableLiveData<List<YemeklerFavoriDB>>{
        return favoriKontrolListesi
    }
    fun favoriKontrolYemeklerr() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            favoriKontrolListesi.value = vt2.yemeklerFavoriDao().tumYemeklerFavori()
        }
    }
    fun favoriGuncelle(favori_id: Int, favori_kontrol: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenFavori = YemeklerFavoriDB(favori_id, favori_kontrol)
            vt2.yemeklerFavoriDao().yemeklerFavoriGuncelle(guncellenenFavori)
        }
    }

}