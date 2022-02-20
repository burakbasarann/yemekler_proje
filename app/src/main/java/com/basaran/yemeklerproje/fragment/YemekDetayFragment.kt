package com.basaran.yemeklerproje.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.basaran.yemeklerproje.R
import com.basaran.yemeklerproje.databinding.FragmentYemekDetayBinding
import com.basaran.yemeklerproje.viewmodel.YemekDetayViewModel
import com.google.android.material.snackbar.Snackbar


class YemekDetayFragment : Fragment() {

    private lateinit var tasarim : FragmentYemekDetayBinding
    private lateinit var viewmodel : YemekDetayViewModel

    private var sonuc = 0
    private var sayac = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_detay, container, false)

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarDetay)
        tasarim.toolbarDetay.title = ""

        val sharedPref = requireContext().getSharedPreferences("KullaniciAdi", Context.MODE_PRIVATE)
        val sharedPrefUserName = sharedPref.getString("userName", "")
        requireContext().getSharedPreferences("KullaniciAdi", Context.MODE_PRIVATE).getString("userName","")
        tasarim.yemekKullaniciAdi = sharedPrefUserName

        val bundle : YemekDetayFragmentArgs by navArgs()
        val gelenFiyat = bundle.yemek.yemek_fiyat.toInt()
        val gelenYemek = bundle.yemek

        tasarim.yemekBundle = bundle.yemek
        tasarim.sepetKayitFragment = this
        viewmodel.detayResimYukle(gelenYemek.yemek_resim_adi,tasarim.imageViewYemekResim)

        tasarim.imageViewArttir.setOnClickListener {
            imageViewAdetArttir(gelenFiyat, tasarim.textViewFiyat, tasarim.textViewAdet)
        }

        tasarim.imageViewAzalt.setOnClickListener {
            val sonuc2 = sonuc - gelenFiyat
            if (sonuc2 >= 0){
                sonuc = sonuc - gelenFiyat
                sayac--
                tasarim.textViewFiyat.text = "₺ $sonuc"
                tasarim.textViewAdet.text = sayac.toString()
            }
        }



        return tasarim.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : YemekDetayViewModel by viewModels()
        viewmodel = tempViewModel
        setHasOptionsMenu(true)
    }
    fun imageViewAdetArttir(gelenFiyat: Int,textViewFiyat: TextView, textViewAdet: TextView){
        sonuc += gelenFiyat
        sayac++
        textViewFiyat.text = "₺ $sonuc"
        textViewAdet.text = sayac.toString()
    }

    fun buttonKaydetTikla(yemek_adi: String, yemek_resim_adi: String, kullanici_adi: String){
        if (tasarim.textViewAdet.text == "0"){
            val sb = Snackbar.make(requireView(), "Adet Seçmediniz", Snackbar.LENGTH_SHORT)
            sb.setTextColor(Color.RED)
            sb.setBackgroundTint(Color.WHITE)
            sb.show()
        }else {
            viewmodel.kayit(yemek_adi, yemek_resim_adi, sonuc, sayac, kullanici_adi)
            val sb = Snackbar.make(requireView(), "Sepete Eklendi", Snackbar.LENGTH_LONG)
            sb.setAction("Sepete Git") {

                Navigation.findNavController(tasarim.root).navigate(R.id.detaydanSepeteGecis)
            }
            sb.setActionTextColor(Color.RED)
            sb.setTextColor(Color.BLUE)
            sb.setBackgroundTint(Color.WHITE)
            sb.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_detay,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_sepet -> {
                Navigation.findNavController(tasarim.root).navigate(R.id.detaydanSepeteGecis)
            }
        }
        return super.onOptionsItemSelected(item)
    }



}