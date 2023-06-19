package com.example.jepretajitu.view.activity

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jepretajitu.BuildConfig
import com.example.jepretajitu.databinding.ActivityProfileBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.viewmodel.LoginViewModel
import com.example.jepretajitu.viewmodel.ProfileViewModel
import java.io.ByteArrayOutputStream

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    lateinit var profileViewModel : ProfileViewModel
    lateinit var sharePreferences : SharedPrefences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        sharePreferences = SharedPrefences(this)
        binding.progressBar.visibility = View.VISIBLE
        binding.btnUbah.setOnClickListener {
            startActivity(Intent(this,UbahProfileActivity::class.java))
            finish()
        }



        profileViewModel.getProfile(sharePreferences.getIntData(Constant.ADD_ID_USER)).observe(this){
            binding.tvNama2.text = it.dataProfile!!.nama
            binding.tvEmail2.text = it.dataProfile.email
            binding.tvNomorHp2.text = it.dataProfile.nomorHp
            binding.tvAlamat2.text = it.dataProfile.alamat
            Glide.with(this).load("http://192.168.1.10:8000" + it.dataProfile.foto).into(binding.imageProfile)
            binding.progressBar.visibility = View.GONE
        }
    }
}