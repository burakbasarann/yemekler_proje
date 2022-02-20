package com.basaran.yemeklerproje.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.basaran.yemeklerproje.R
import com.basaran.yemeklerproje.databinding.AnasayfaYemeklerTasarimBinding
import com.basaran.yemeklerproje.entity.Yemekler
import com.basaran.yemeklerproje.fragment.*
import com.basaran.yemeklerproje.viewmodel.AnaSayfaViewModel

class YemeklerAdapter(var mContext: Context, var yemeklerListesi: List<Yemekler>, var viewmodel:AnaSayfaViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(cardTasarimBinding: AnasayfaYemeklerTasarimBinding)
        : RecyclerView.ViewHolder(cardTasarimBinding.root){
        var cardTasarimBinding: AnasayfaYemeklerTasarimBinding

        init {
            this.cardTasarimBinding = cardTasarimBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = AnasayfaYemeklerTasarimBinding.inflate(layoutInflater,parent,false)
        return CardTasarimTutucu(tasarim)

    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.cardTasarimBinding
        t.yemekNesnesi = yemek
        viewmodel.yemeklerResimYukle(yemek.yemek_resim_adi, t.imgResim)
        if (viewmodel.yemeklerFavoriKontrolListesi.value?.get(position)?.favori==0){
            t.imgResimFavori.setImageResource(R.drawable.favoribos)
        } else{
            t.imgResimFavori.setImageResource(R.drawable.favoridolu)
        }

        t.imgResimFavori.setOnClickListener {
            if(viewmodel.yemeklerFavoriKontrolListesi.value?.get(position)?.favori == 0) {
                t.imgResimFavori.setImageResource(R.drawable.favoridolu)
                viewmodel.kayitFavori(yemek.yemek_id.toInt() ,yemek.yemek_adi,yemek.yemek_resim_adi,yemek.yemek_fiyat)
                viewmodel.guncelle(yemek.yemek_id.toInt(),1)
            }
            else{
                t.imgResimFavori.setImageResource(R.drawable.favoribos)
                viewmodel.silFavori(yemek.yemek_id.toInt())
                viewmodel.guncelle(yemek.yemek_id.toInt(),0)
            }

        }
        t.satirCard.setOnClickListener {
            val gecis = AnaSayfaFragmentDirections.yemekDetayGecis(yemek)
            Navigation.findNavController(it).navigate(gecis)
        }

    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }


}
