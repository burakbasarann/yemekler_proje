package com.basaran.yemeklerproje.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basaran.yemeklerproje.databinding.SepetYemekTasarimBinding
import com.basaran.yemeklerproje.entity.SepetYemekler
import com.basaran.yemeklerproje.viewmodel.SepetViewModel

class SepetAdapter(var mContext: Context, var sepetYemeklerListesi: List<SepetYemekler>, var viewModel: SepetViewModel)
    : RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu>() {

    val list = arrayListOf<SepetYemekler>()

    inner class CardTasarimTutucu(cardTasarimBinding: SepetYemekTasarimBinding) : RecyclerView.ViewHolder(cardTasarimBinding.root) {

        var cardTasarimBinding: SepetYemekTasarimBinding

        init {
            this.cardTasarimBinding = cardTasarimBinding

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = SepetYemekTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val sepetYemek = sepetYemeklerListesi.get(position)
        val t = holder.cardTasarimBinding
        t.sepetYemekNesnesi = sepetYemek
        viewModel.sepetResimYukle(sepetYemek.yemek_resim_adi, t.imgSepetResim)

          t.imageViewSil.setOnClickListener {
            if (sepetYemeklerListesi.size == 1) {
              t.satirCard.visibility = View.GONE
                viewModel.sepetYemeklerListesi.value = emptyList()
            }
            viewModel.sepetUrunSil(sepetYemek.sepet_yemek_id.toInt(), sepetYemek.kullanici_adi)

        }

    }

    override fun getItemCount(): Int {
        return sepetYemeklerListesi.size
    }
}