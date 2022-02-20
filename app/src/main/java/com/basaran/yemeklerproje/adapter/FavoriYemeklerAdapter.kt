package com.basaran.yemeklerproje.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.basaran.yemeklerproje.databinding.FavoriYemeklerTasarimBinding
import com.basaran.yemeklerproje.entity.Yemekler
import com.basaran.yemeklerproje.entity.YemeklerRoomDB
import com.basaran.yemeklerproje.fragment.FavorilerFragmentDirections
import com.basaran.yemeklerproje.viewmodel.FavorilerViewModel

class FavoriYemeklerAdapter(var mContext : Context, var yemeklerFavoriList: List<YemeklerRoomDB>, var viewModel: FavorilerViewModel)
    : RecyclerView.Adapter<FavoriYemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(cardTasarimBinding: FavoriYemeklerTasarimBinding) : RecyclerView.ViewHolder(cardTasarimBinding.root){
        var cardTasarimBinding: FavoriYemeklerTasarimBinding
        init {
            this.cardTasarimBinding = cardTasarimBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = FavoriYemeklerTasarimBinding.inflate(layoutInflater , parent , false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemekFavori = yemeklerFavoriList.get(position)
        val t = holder.cardTasarimBinding
        t.yemekFavoriNesnesi = yemekFavori
        viewModel.favoriResimYukle(yemekFavori.yemek_resim_adi, t.imgFavoriResim)
        val yemekler = Yemekler(yemekFavori.yemek_id.toString(), yemekFavori.yemek_adi, yemekFavori.yemek_resim_adi, yemekFavori.yemek_fiyat )
        t.satirCard.setOnClickListener {
            val gecis = FavorilerFragmentDirections.favorilerdenDetayaGecis(yemekler)
            Navigation.findNavController(it).navigate(gecis)
        }
        t.imageViewSil.setOnClickListener {
            viewModel.guncelle(yemekFavori.yemek_id,0)
            viewModel.silFavori(yemekFavori.yemek_id)
        }
    }

    override fun getItemCount(): Int {
        return yemeklerFavoriList.size
    }
}