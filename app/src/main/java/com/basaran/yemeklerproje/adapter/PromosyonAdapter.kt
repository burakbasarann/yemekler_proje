package com.basaran.yemeklerproje.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basaran.yemeklerproje.databinding.AnasayfaPromosyonTasarimBinding
import com.basaran.yemeklerproje.entity.Promosyon

class PromosyonAdapter(var mContext : Context, var promosyonListesi: List<Promosyon>) : RecyclerView.Adapter<PromosyonAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(cardTasarimBinding: AnasayfaPromosyonTasarimBinding) : RecyclerView.ViewHolder(cardTasarimBinding.root){
        var cardTasarimBinding: AnasayfaPromosyonTasarimBinding

        init {
            this.cardTasarimBinding = cardTasarimBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = AnasayfaPromosyonTasarimBinding.inflate(layoutInflater , parent , false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val promosyon = promosyonListesi.get(position)
        val t = holder.cardTasarimBinding
        t.promosyonNesnesi = promosyon

        t.imgPromosyon.setImageResource(mContext.resources.getIdentifier(promosyon.promosyonResim,"drawable", mContext.packageName))

    }

    override fun getItemCount(): Int {
        return promosyonListesi.size
    }
}

