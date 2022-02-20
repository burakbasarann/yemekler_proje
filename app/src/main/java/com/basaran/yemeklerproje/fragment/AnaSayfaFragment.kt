package com.basaran.yemeklerproje.fragment


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.basaran.yemeklerproje.R
import com.basaran.yemeklerproje.adapter.PromosyonAdapter
import com.basaran.yemeklerproje.adapter.YemeklerAdapter
import com.basaran.yemeklerproje.databinding.FragmentAnaSayfaBinding
import com.basaran.yemeklerproje.viewmodel.AnaSayfaViewModel
import com.basaran.yemeklerproje.viewmodel.AnasayfaVMF


class AnaSayfaFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var tasarim: FragmentAnaSayfaBinding
    private lateinit var yemeklerAdapter: YemeklerAdapter
    private lateinit var promosyonAdapter: PromosyonAdapter
    private lateinit var viewModel: AnaSayfaViewModel
    private lateinit var kullaniciAdi: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_ana_sayfa, container, false)
        tasarim.anasayfaFragment = this
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbar)
        tasarim.toolbar.title = ""
        var sayacFiyat = 0
        var sayacAlfabe = 0

        viewModel.yemeklerListesi.observe(viewLifecycleOwner, {
            yemeklerAdapter = YemeklerAdapter(requireContext(), it, viewModel)
            tasarim.yemeklerAdapter = yemeklerAdapter
            viewModel.yemeklerListesiFiyat = viewModel.yemeklerListesi.value

            tasarim.buttonAlfteSiralama.setOnClickListener {
                if (sayacAlfabe == 0) {
                    viewModel.alfabeyeGoreSiralama()
                    sayacAlfabe++
                } else {
                    viewModel.alfabeyeGoreSiralamaTersi()
                    sayacAlfabe = 0
                }
            }
            tasarim.buttonKucuktenBuyuge.setOnClickListener {
                if (sayacFiyat == 0) {
                    viewModel.kucuktenBuyuge()
                    sayacFiyat++
                } else {
                    viewModel.buyuktenKucuge()
                    sayacFiyat = 0
                }
            }
        })

        promosyonAdapter = PromosyonAdapter(requireContext(), viewModel.promosyonListesi)
        tasarim.promosyonAdapter = promosyonAdapter

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AnaSayfaViewModel by viewModels(){
            AnasayfaVMF(requireActivity().application)
        }
        viewModel = tempViewModel
        setHasOptionsMenu(true)
        val sharedPref = requireContext().getSharedPreferences("KullaniciAdi", Context.MODE_PRIVATE)
        val sharedPrefUserName = sharedPref.getString("userName", "")
        kullaniciAdi = sharedPrefUserName!!

    }

    override fun onResume() {
        viewModel.yemekleriYukle()
        viewModel.favoriYemekKontrolYukle()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_sepet -> {
                Navigation.findNavController(tasarim.root).navigate(R.id.sepetGecis)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.yemekleriYukle()
        viewModel.favoriYemekKontrolYukle()
        viewModel.arama(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {

        viewModel.arama(newText)
        tasarim.rvPromosyonlist.visibility = View.GONE
        if (newText.length == 0) {
            viewModel.yemekleriYukle()
            viewModel.favoriYemekKontrolYukle()
            tasarim.rvPromosyonlist.visibility = View.VISIBLE
        }
        return true
    }
    fun buttonFavorilerGecis(){
        Navigation.findNavController(tasarim.root).navigate(R.id.anaSayfadanFavorilereGecis)
    }
    fun buttonSiparisGecmisineGecis(){
        Navigation.findNavController(tasarim.root).navigate(R.id.anaSayfadanGecmisSiparislereGecis)
    }

}
