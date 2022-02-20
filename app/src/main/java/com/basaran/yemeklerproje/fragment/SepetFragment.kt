package com.basaran.yemeklerproje.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.basaran.yemeklerproje.R
import com.basaran.yemeklerproje.adapter.SepetAdapter
import com.basaran.yemeklerproje.databinding.FragmentSepetBinding
import com.basaran.yemeklerproje.entity.SepetYemekler
import com.basaran.yemeklerproje.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar


class SepetFragment : Fragment() {
    private lateinit var tasarim: FragmentSepetBinding
    private lateinit var sepetAdapter: SepetAdapter
    private lateinit var viewModel: SepetViewModel
    private var toplamFiyat = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)

        val sharedPref = requireContext().getSharedPreferences("KullaniciAdi", Context.MODE_PRIVATE)
        val sharedPrefUserName = sharedPref.getString("userName", "")
        viewModel.sepetYemekAl(sharedPrefUserName!!)

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner, {
            if (viewModel.sepetYemeklerListesi.value!!.size > 0) {
                sepetAdapter = SepetAdapter(requireContext(), it, viewModel)
                tasarim.sepetAdapter = sepetAdapter
                viewModel.sepetYemeklerListesiFiyat = viewModel.sepetYemeklerListesi.value as ArrayList<SepetYemekler>?
                toplamFiyat = viewModel.sepetYemeklerListesiFiyat?.sumOf { it.yemek_fiyat.toInt() }!!

            } else {
                tasarim.sepetAdapter = null
                toplamFiyat = 0
            }
            tasarim.textViewSepetFiyat.text = "$toplamFiyat ₺"
            tasarim.buttonSiparisiTamamla.setOnClickListener {
                if (viewModel.sepetYemeklerListesi.value!!.size > 0) {
                    for (i in 0..viewModel.sepetYemeklerListesiFiyat?.size!! - 1) {
                        val list = viewModel.sepetYemeklerListesiFiyat?.get(i)
                        viewModel.kayitSiparis(list?.sepet_yemek_id!!.toInt(), list.yemek_adi, list.yemek_resim_adi, list.yemek_fiyat, list.yemek_siparis_adet, list.kullanici_adi
                        )
                    }
                    Navigation.findNavController(it).navigate(R.id.siparisGecis)
                } else {
                    val sb = Snackbar.make(requireView(), "Sepetiniz Boş", Snackbar.LENGTH_SHORT)
                    sb.setTextColor(Color.RED)
                    sb.setBackgroundTint(Color.WHITE)
                    sb.show()
                }
            }
        })
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetViewModel by viewModels()
        viewModel = tempViewModel
    }
}