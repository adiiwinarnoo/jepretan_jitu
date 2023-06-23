package com.example.jepretajitu.view.activity.user

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jepretajitu.databinding.ActivityPaymentBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.viewmodel.TransaksiViewModel
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class PaymentActivity : AppCompatActivity() {

    lateinit var binding : ActivityPaymentBinding
    lateinit var viewModel : TransaksiViewModel
    lateinit var sharedPref : SharedPrefences
    val myCalendar = Calendar.getInstance()
    var tanggal = ""
    var imageFoto = ""
    var REQUEST_CODE_GALERI = 100
    var idProduct = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idProduct = intent.getIntExtra("ID-PRODUCT",0)

        viewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
        sharedPref = SharedPrefences(this)

        val date = OnDateSetListener { datePicker, year, month, day ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = day
            val format = "YYYY/MM/dd"
            val dateFormat = SimpleDateFormat(format, Locale.US)
            binding.edtTanggal.setText(dateFormat.format(myCalendar.time))
            tanggal = dateFormat.format(myCalendar.time)
        }

        binding.edtTanggal.setOnClickListener {
            DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.edtSlipPembayaran.setOnClickListener {
            selectImage()
        }

        binding.btnPesan.setOnClickListener {
            checkedData()
        }



        viewModel.dataTransaksi.observe(this){
            Toast.makeText(this, "Pesanan anda berhasil, silahkan tunggu!", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE_GALERI) && (resultCode == RESULT_OK) && (data != null)) {
            var imageUri = data.data
            try {
                var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                var byteOut = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteOut)
                var imageBytes = byteOut.toByteArray()
                imageFoto = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT)
                binding.imageSlipPembayaran.visibility = View.VISIBLE
                binding.imageSlipPembayaran.setImageBitmap(bitmap)
                println("yoo")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            binding.imageSlipPembayaran.setImageURI(imageUri)
        }
    }
    private fun selectImage(){
        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(
            Intent.createChooser(intent,"Pilih Gambar"),
            REQUEST_CODE_GALERI)
    }

    private fun checkedData(){
        if (binding.edtTanggal.text.isNullOrEmpty()) Toast.makeText(this, "Tanggal tidak boleh kosong", Toast.LENGTH_SHORT).show()
        else if (binding.edtAlamat.text.isNullOrEmpty()) Toast.makeText(this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
        else if (imageFoto.isNullOrEmpty()) Toast.makeText(this, "Wajib menyertakan slip pembayaran", Toast.LENGTH_SHORT).show()
        else{
            var alamat = binding.edtAlamat.text.toString()
            viewModel.sendPayment(sharedPref.getIntData(Constant.ADD_ID_USER),idProduct,alamat,tanggal,imageFoto,"sudah transfer")
        }
    }
}