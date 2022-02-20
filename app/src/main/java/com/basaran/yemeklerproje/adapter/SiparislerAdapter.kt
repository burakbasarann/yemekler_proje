package com.basaran.yemeklerproje.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basaran.yemeklerproje.databinding.CardviewGecmisSiparislerTasarimBinding
import com.basaran.yemeklerproje.entity.YemeklerSiparisDB
import com.basaran.yemeklerproje.viewmodel.GecmisSiparisViewModel

class SiparislerAdapter(var mContext : Context, var siparisListesi: List<YemeklerSiparisDB>, var viewModel: GecmisSiparisViewModel)
    : RecyclerView.Adapter<SiparislerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(cardTasarimBinding: CardviewGecmisSiparislerTasarimBinding) : RecyclerView.ViewHolder(cardTasarimBinding.root){
        var cardTasarimBinding: CardviewGecmisSiparislerTasarimBinding
        init {
            this.cardTasarimBinding = cardTasarimBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardviewGecmisSiparislerTasarimBinding.inflate(layoutInflater , parent , false)
        tasarim.siparislerAdapter = this
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemekSiparis = siparisListesi.get(position)
        val t = holder.cardTasarimBinding
        t.yemekSiparisNesnesi = yemekSiparis
        viewModel.siparisResimYukle(yemekSiparis.yemek_resim_adi, t.imgFavoriResim)


        t.imageViewSil.setOnClickListener {
            imageViewSil(yemekSiparis.siparis_id, yemekSiparis.sepet_yemek_id)
        }

    }

    override fun getItemCount(): Int {
        return siparisListesi.size
    }

    fun imageViewSil(siparis_id: Int, sepet_id: Int){
        viewModel.silSiparis(siparis_id, sepet_id)
    }
}