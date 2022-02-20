package com.basaran.yemeklerproje.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.basaran.yemeklerproje.R
import com.basaran.yemeklerproje.adapter.FavoriYemeklerAdapter
import com.basaran.yemeklerproje.databinding.FragmentFavorilerBinding
import com.basaran.yemeklerproje.viewmodel.FavorilerVMF
import com.basaran.yemeklerproje.viewmodel.FavorilerViewModel


class FavorilerFragment : Fragment() {

    private lateinit var tasarim: FragmentFavorilerBinding
    private lateinit var viewModel: FavorilerViewModel
    private lateinit var adapter: FavoriYemeklerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_favoriler, container, false)
        tasarim.favorilerFragment = this

        viewModel.yemeklerFavoriListesi.observe(viewLifecycleOwner, {

            adapter = FavoriYemeklerAdapter(requireContext(),it, viewModel)
            tasarim.favorilerAdapter = adapter
        })
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FavorilerViewModel by viewModels(){
            FavorilerVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun buttonAnasayfaGecis(){
        Navigation.findNavController(tasarim.root).navigate(R.id.favoridenAnasayfayaGecis)
    }

}