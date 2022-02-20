package com.basaran.yemeklerproje.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.basaran.yemeklerproje.R
import com.basaran.yemeklerproje.databinding.FragmentSiparisBinding


class SiparisFragment : Fragment() {

    private lateinit var tasarim : FragmentSiparisBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = FragmentSiparisBinding.inflate(inflater, container, false)

        val ad = AlertDialog.Builder(requireContext())
        tasarim.buttonDegisiklik.setOnClickListener {
       ad.setMessage("Değişiklikler Kaydedildi")
        ad.setTitle("Değişiklikler")
        ad.setIcon(R.drawable.onay)
        ad.setPositiveButton("AnaSayfaya Git"){ dialogInterface, i ->
            Navigation.findNavController(tasarim.textView3).navigate(R.id.siparistenAnaSayfayaGecis)
        }
        ad.setNegativeButton("Siparişlerine Git") {dialogInterface, i ->
        Navigation.findNavController(tasarim.textView3).navigate(R.id.siparistenGecmisSiparislereGecis)
        }
            ad.create().show()
        }

        return tasarim.root
    }


}