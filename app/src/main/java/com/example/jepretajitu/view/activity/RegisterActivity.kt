package com.example.jepretajitu.view.activity

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.example.jepretajitu.databinding.ActivityRegisterBinding
import java.io.ByteArrayOutputStream
import java.util.*

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    var REQUEST_CODE_GALERI = 100
    var name : String? = null
    var email : String? = null
    var password : String? = null
    var nomorHp : String? = null
    var alamat : String? = null
    var imageFoto : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageProfile.setOnClickListener {
            selectImage()
        }
        binding.btnSimpanRegister.setOnClickListener {
            name = binding.edtName.text.toString()
            email = binding.edtEmail.text.toString()
            password = binding.edtPassword.text.toString()
            nomorHp = binding.edtNomorHp.text.toString()

            try {
                nomorHp?.toInt()
            }catch (e : Exception){
                null
            }
            alamat = binding.edtAlamat.text.toString()
            Log.d("payload-register", "onCreate: nama = $name, " +
                    "email = $email, password = $password, nomorHp = $nomorHp, alamat = $alamat")
        }
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
}