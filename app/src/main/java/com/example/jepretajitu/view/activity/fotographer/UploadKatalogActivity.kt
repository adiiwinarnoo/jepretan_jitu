package com.example.jepretajitu.view.activity.fotographer

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
import com.example.jepretajitu.R
import com.example.jepretajitu.databinding.ActivityUploadKatalogBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.view.activity.LoginActivity
import com.example.jepretajitu.viewmodel.AddKatalogViewModel
import java.io.ByteArrayOutputStream

class UploadKatalogActivity : AppCompatActivity() {

    lateinit var binding : ActivityUploadKatalogBinding
    lateinit var uploadKatalogViewModel : AddKatalogViewModel
    lateinit var sharedPrefences: SharedPrefences
    var imageOne : String? = null
    var imageTwo : String? = null
    var imageThree : String? = null
    var idUser = 0
    var judulProduct = ""
    var hargaProduct = ""
    var nomorWhatsapp = ""
    var domisili = ""
    var deskripsi = ""
    private var REQUEST_CODE_GALERI = 1000
    private var REQUEST_CODE_GALERI_TWO = 2000
    private var REQUEST_CODE_GALERI_THREE = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadKatalogBinding.inflate(layoutInflater)
        sharedPrefences = SharedPrefences(this)
        uploadKatalogViewModel = ViewModelProvider(this)[AddKatalogViewModel::class.java]
        setContentView(binding.root)

        binding.btnUploadKatalog.setOnClickListener {
            checkedData()
        }
        binding.imgAdd.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent,"Pilih Gambar"), REQUEST_CODE_GALERI)
        }
        binding.imgAdd2.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent,"Pilih Gambar"), REQUEST_CODE_GALERI_TWO)
        }
        binding.imgAdd3.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent,"Pilih Gambar"), REQUEST_CODE_GALERI_THREE)
        }

        uploadKatalogViewModel.katalogData.observe(this){
            Log.d("UPLOAD-KATALOG-FAILED", "onCreate: ${it.message}")
            when (it.message){
                "Upload katalog anda berhasil" -> {
                    Toast.makeText(this, "Berhasil Upload Katalog!", Toast.LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE
                    startActivity(Intent(this, AddFotoActivity::class.java))
                    finish()
                }
                "Upload katalog anda gagal" ->{
                    Toast.makeText(this, "Gagal Upload Katalog!", Toast.LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE
                }
                "Maaf anda sudah mendaftarkan katalog anda!" -> {
                    Toast.makeText(this, "Maaf anda sudah mendaftarkan katalog anda!", Toast.LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_GALERI -> {
                var imageUri = data?.data
                try {
                    var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,imageUri)
                    var byteOut = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteOut)
                    var imageBytes = byteOut.toByteArray()
                    imageOne = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT)
                    binding.imgAdd.setImageBitmap(bitmap)
                }
                catch (e : Exception){
                    e.printStackTrace()
                }
                binding.imgAdd.setImageURI(imageUri)
            }
            REQUEST_CODE_GALERI_TWO -> {
                var imageUri = data?.data
                try {
                    var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,imageUri)
                    var byteOut = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteOut)
                    var imageBytes = byteOut.toByteArray()
                    imageTwo = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT)
                    binding.imgAdd2.setImageBitmap(bitmap)
                }
                catch (e : Exception){
                    e.printStackTrace()
                }
                binding.imgAdd2.setImageURI(imageUri)
            }
            REQUEST_CODE_GALERI_THREE -> {
                var imageUri = data?.data
                try {
                    var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,imageUri)
                    var byteOut = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteOut)
                    var imageBytes = byteOut.toByteArray()
                    imageThree = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT)
                    binding.imgAdd3.setImageBitmap(bitmap)
                }
                catch (e : Exception){
                    e.printStackTrace()
                }
                binding.imgAdd3.setImageURI(imageUri)
            }
        }
    }


    private fun checkedData(){
        if (binding.edtDomisili.text.toString().isNullOrEmpty()) Toast.makeText(this,
            R.string.editext_domisili_null, Toast.LENGTH_SHORT).show()
        else if (binding.edtDeskripsi.text.toString().isNullOrEmpty()) Toast.makeText(this,
            R.string.editext_deskripsi_null, Toast.LENGTH_SHORT).show()
        else if (binding.edtHarga.text.toString().isNullOrEmpty()) Toast.makeText(this,
            R.string.editext_harga_null, Toast.LENGTH_SHORT).show()
        else if (binding.edtJudul.text.toString().isNullOrEmpty()) Toast.makeText(this,
            R.string.editext_judul_null, Toast.LENGTH_SHORT).show()
        else if (imageOne.isNullOrEmpty() || imageTwo.isNullOrEmpty() || imageThree.isNullOrEmpty())
            Toast.makeText(this, R.string.image_null, Toast.LENGTH_SHORT).show()
        else{
            binding.progressbar.visibility = View.VISIBLE
            idUser = sharedPrefences.getIntData(Constant.ADD_ID_USER)
            judulProduct = binding.edtJudul.text.toString()
            hargaProduct = binding.edtHarga.text.toString()
            nomorWhatsapp = sharedPrefences.getStringData(Constant.ADD_NOMOR_WHATSAPP)!!
            domisili = binding.edtDomisili.text.toString()
            deskripsi = binding.edtDeskripsi.text.toString()
            uploadKatalogViewModel.sendKatalog(idUser,imageOne!!,imageTwo!!,imageThree!!,
                judulProduct,nomorWhatsapp,deskripsi,domisili,hargaProduct)
        }
    }
}