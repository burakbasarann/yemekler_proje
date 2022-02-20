package com.basaran.yemeklerproje.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.basaran.yemeklerproje.R
import com.basaran.yemeklerproje.adapter.FavoriYemeklerAdapter
import com.basaran.yemeklerproje.adapter.SiparislerAdapter
import com.basaran.yemeklerproje.databinding.FragmentGecmisSiparislerBinding
import com.basaran.yemeklerproje.viewmodel.*


class GecmisSiparislerFragment : Fragment() {

    private lateinit var viewModel: GecmisSiparisViewModel
    private lateinit var tasarim: FragmentGecmisSiparislerBinding
    private lateinit var adapter: SiparislerAdapter
    private lateinit var kullaniciAdi: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_gecmis_siparisler, container, false)

        val sharedPref = requireContext().getSharedPreferences("KullaniciAdi", Context.MODE_PRIVATE)
        val sharedPrefUserName = sharedPref.getString("userName", "")
        kullaniciAdi = sharedPrefUserName!!

        viewModel.siparislerListesi.observe(viewLifecycleOwner, {
            tasarim.siparisAdapter = null
            for(i in 0..viewModel.siparislerListesi.value?.size!!-1) {
                if (kullaniciAdi == viewModel.siparislerListesi.value!![i].kullanici_adi) {
                    adapter = SiparislerAdapter(requireContext(), it, viewModel)
                    tasarim.siparisAdapter = adapter
                }
            }
        })
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : GecmisSiparisViewModel by viewModels(){
            GecmisSiparisVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

}