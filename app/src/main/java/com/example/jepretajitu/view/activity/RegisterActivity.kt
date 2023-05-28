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
import com.example.jepretajitu.databinding.ActivityRegisterBinding
import com.example.jepretajitu.viewmodel.RegisterViewModel
import java.io.ByteArrayOutputStream
import java.util.*

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    lateinit var registerViewModel : RegisterViewModel
    var REQUEST_CODE_GALERI = 100
    var name : String? = null
    var email : String? = null
    var password : String? = null
    var nomorHp : String? = null
    var alamat : String? = null
    var imageFoto : String? = null
    var levelId : Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.imageProfile.setOnClickListener {
            selectImage()
        }
        binding.btnSimpanRegister.setOnClickListener {
            checkedData()
        }

        registerViewModel.registerData.observe(this){
            when (it.message){
                "Register Success" -> {
                    Toast.makeText(this, "Berhasil mendaftar!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                "Your email is registered!" -> {
                    Toast.makeText(this,
                        "Email sudah terdaftar!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun sendRegister(name : String, email : String, password : String, nomorHp : String, alamat : String,
                     imageFoto : String?,levelId : Int){
        registerViewModel.sendRegister(name, email,nomorHp, password,levelId,imageFoto,alamat)

    }

    fun selectImage(){
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent,"Pilih Gambar"),
            REQUEST_CODE_GALERI)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE_GALERI) && (resultCode == RESULT_OK) && (data != null)){
            var imageUri = data.data
            try {
                var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,imageUri)
                var byteOut = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteOut)
                var imageBytes = byteOut.toByteArray()
                imageFoto = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT)
                binding.imageProfile.setImageBitmap(bitmap)
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
        else if (binding.edtPassword.text.isNullOrEmpty()) Toast.makeText(this, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
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
            sendRegister(name!!, email!!, password!!, nomorHp!!, alamat!!, imageFoto, levelId)
        }
    }
}