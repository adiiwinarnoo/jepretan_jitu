package com.example.jepretajitu.view.activity

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jepretajitu.databinding.ActivityUbahProfileBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.viewmodel.ProfileViewModel
import com.example.jepretajitu.viewmodel.RegisterViewModel
import java.io.ByteArrayOutputStream

class UbahProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityUbahProfileBinding
    lateinit var profileViewModel : ProfileViewModel
    lateinit var registerViewModel : RegisterViewModel
    lateinit var sharePreferences : SharedPrefences
    var imageFoto : String? = null
    var REQUEST_CODE_GALERI = 100
    var name : String? = null
    var email : String? = null
    var password : String? = null
    var nomorHp : String? = null
    var alamat : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        sharePreferences = SharedPrefences(this)

        binding.imageProfile.setOnClickListener {
            selectImage()
        }

        binding.btnSimpanRegister.setOnClickListener {
            checkedData()
        }


        profileViewModel.getProfile(sharePreferences.getIntData(Constant.ADD_ID_USER)).observe(this){
            binding.edtName.setText(it.dataProfile!!.nama)
            binding.edtAlamat.setText(it.dataProfile.alamat)
            binding.edtEmail.setText(it.dataProfile.email)
            binding.edtNomorHp.setText(it.dataProfile.nomorHp)
            Glide.with(this).load("http://192.168.1.10:8000" + it.dataProfile.foto).into(binding.imageProfile)
        }
    }


    fun selectImage(){
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(
            Intent.createChooser(intent,"Pilih Gambar"),
            REQUEST_CODE_GALERI)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE_GALERI) && (resultCode == RESULT_OK) && (data != null)){
            var imageUri = data.data
            Log.d("IMAGE-FOTO", "onActivityResult: $imageUri")
            try {
                var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,imageUri)
                var byteOut = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteOut)
                var imageBytes = byteOut.toByteArray()
                imageFoto = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT)
                binding.imageProfile.setImageBitmap(bitmap)
                println("IYA")
            }
            catch (e : Exception){
                e.printStackTrace()
            }
            binding.imageProfile.setImageURI(imageUri)
        }
    }


    private fun checkedData(){
        if (binding.edtName.text.isNullOrEmpty()) Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
        else if (binding.edtEmail.text.isNullOrEmpty()) Toast.makeText(this, "Email tidak boleh kosong!", Toast.LENGTH_SHORT).show()
        else if (binding.edtNomorHp.text.isNullOrEmpty()) Toast.makeText(this, "Nomor HP tidak boleh kosong!", Toast.LENGTH_SHORT).show()
        else if (binding.edtAlamat.text.isNullOrEmpty()) Toast.makeText(this, "Alamat tidak boleh kosong!", Toast.LENGTH_SHORT).show()
        else if (imageFoto.isNullOrEmpty()) Toast.makeText(this, "Silahkan pilih gambar dahulu sebelum menyimpan data",
            Toast.LENGTH_SHORT).show()
        else{
            name = binding.edtName.text.toString()
            email = binding.edtEmail.text.toString()
            password = binding.edtPassword.text.toString()
            nomorHp = binding.edtNomorHp.text.toString()
            alamat = binding.edtAlamat.text.toString()
            try {
                nomorHp?.toInt()
            }catch (e : Exception){
                Log.d("IMAGE-ENCODE", "onCreate: ${e.message}")
            }
            binding.progressBar.visibility = View.VISIBLE
            registerViewModel.sendUpdateProfile(sharePreferences.getIntData(Constant.ADD_ID_USER),name!!,email!!,nomorHp!!,alamat!!,imageFoto).observe(this){
                Toast.makeText(this, "Ubah data berhasil", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
    }
}