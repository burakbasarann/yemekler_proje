package com.basaran.yemeklerproje.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.basaran.yemeklerproje.R
import com.basaran.yemeklerproje.databinding.FragmentLoginEkraniBinding


class LoginEkraniFragment : Fragment() {
    private lateinit var tasarim: FragmentLoginEkraniBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_login_ekrani, container, false)

        val sharedPref = requireContext().getSharedPreferences("KullaniciAdi", Context.MODE_PRIVATE)
        val sharedPrefUserName = sharedPref.getString("userName", "")
        val sharedPrefBeniHatirla = sharedPref.getBoolean("beniHatirla", false)


        if (sharedPrefBeniHatirla == true) {
            tasarim.editTextKullaniciAdi.setText(sharedPrefUserName)
            tasarim.checkBoxHatirla.isChecked = true

        }else{
            tasarim.editTextKullaniciAdi.setText("")
        }

        val sharedPrefEditor = sharedPref.edit()
        val kullaniciAdi = tasarim.editTextKullaniciAdi.text.toString()

        tasarim.buttonGiris.setOnClickListener {
            if (tasarim.checkBoxHatirla.isChecked) {
                saveOrClearUserNameAndRememberMe(sharedPrefEditor, tasarim.editTextKullaniciAdi.text.toString(), true)
            } else {
                saveOrClearUserNameAndRememberMe(sharedPrefEditor, tasarim.editTextKullaniciAdi.text.toString(), false)
            }
            if(kullaniciAdi == " "){
                Toast.makeText(requireContext(),"Kullanıcı Adı Giriniz", Toast.LENGTH_LONG).show()
            }
            else {
                Navigation.findNavController(it).navigate(R.id.logindenAnaSayfayaGecis)
            }
        }

        return tasarim.root
    }

    private fun saveOrClearUserNameAndRememberMe(sharedPrefEditor: SharedPreferences.Editor, userName: String,beniHatirla: Boolean) {
        sharedPrefEditor.putString("userName", userName)
        sharedPrefEditor.putBoolean("beniHatirla", beniHatirla)
        sharedPrefEditor.apply()
    }

}