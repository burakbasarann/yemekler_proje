package com.basaran.yemeklerproje.repository

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.basaran.yemeklerproje.entity.*
import com.basaran.yemeklerproje.retrofit.ApiUtils
import com.basaran.yemeklerproje.retrofit.YemeklerDaoInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository() {
    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var promosyonListesi: ArrayList<Promosyon>
    var sepetYemeklerListesi: MutableLiveData<List<SepetYemekler>>
    var ydao : YemeklerDaoInterface

    init {

        ydao = ApiUtils.getYemeklerDaoInterface()
        yemeklerListesi = MutableLiveData()
        sepetYemeklerListesi = MutableLiveData()
        promosyonListesi = ArrayList()

    }

    fun tumYemekleriAl(){
        ydao.tumYemekler().enqueue(object : Callback<TumYemekler> {
            override fun onResponse(call: Call<TumYemekler>, response: Response<TumYemekler>) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }
            override fun onFailure(call: Call<TumYemekler>?, t: Throwable?) {}
        })
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }
    fun promosyonGetir() : ArrayList<Promosyon>{
        return promosyonListesi
    }
    fun aramaYemekler(aramaKelimesi: String): List<Yemekler>{
        return yemekleriGetir().value!!.filter { it.yemek_adi.contains(aramaKelimesi,true) }
    }


    fun sepeteKayit(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String){
        ydao.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>?) {}
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun sepetYemekleriGetir() : MutableLiveData<List<SepetYemekler>> {
        return sepetYemeklerListesi
    }


    fun sepettekileriGetir(kullanici_adi: String){
        ydao.sepettekileriGetir(kullanici_adi).enqueue(object : Callback<TumSepetYemekler>{
            override fun onResponse(call: Call<TumSepetYemekler>, response: Response<TumSepetYemekler>) {
                val sepetListe = response.body().sepet_yemekler
                sepetYemeklerListesi.value = sepetListe
            }
            override fun onFailure(call: Call<TumSepetYemekler>?, t: Throwable?) {}
        })
    }

    fun sepetUrunSil(sepet_yemek_id: Int, kullanici_adi: String){
        ydao.sepetUrunSil(sepet_yemek_id, kullanici_adi).enqueue(object  : Callback<TumSepetYemekler>{
            override fun onResponse(call: Call<TumSepetYemekler>, response: Response<TumSepetYemekler>) {
                sepettekileriGetir(kullanici_adi)
            }
            override fun onFailure(call: Call<TumSepetYemekler>?, t: Throwable?) {}
        })
    }



    fun resimTutucu(resimAdi: String, imgResim: ImageView){
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$resimAdi"
        Picasso.get()
            .load(url)
            .into(imgResim)
    }

    fun promosyonResimleri(){
        val p1 = Promosyon("promosyon1")
        val p2 = Promosyon("promosyon2")
        val p3 = Promosyon("promosyon3")
        val p4 = Promosyon("promosyon4")
        val p5 = Promosyon("promosyon5")
        val p6 = Promosyon("promosyon6")
        promosyonListesi.add(p3)
        promosyonListesi.add(p2)
        promosyonListesi.add(p1)
        promosyonListesi.add(p4)
        promosyonListesi.add(p5)
        promosyonListesi.add(p6)
    }
}