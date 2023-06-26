package com.example.jepretajitu.view.activity.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.jepretajitu.databinding.ActivityUlasanBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.viewmodel.KatalogViewModel

class UlasanActivity : AppCompatActivity() {

    lateinit var binding : ActivityUlasanBinding
    lateinit var viewModel : KatalogViewModel
    lateinit var sharedPref : SharedPrefences
    var ratingValue = 0
    var reviewUlasan = ""
    var idProduct = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUlasanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(KatalogViewModel::class.java)
        sharedPref = SharedPrefences(this)

        idProduct = intent.getIntExtra("ID-PRODUCT-DETAIL",0)

        binding.ratingbar.setOnRatingBarChangeListener { ratingBar, rating, fromuser ->
            ratingValue = rating.toInt()
        }


        binding.btnUploadKatalog.setOnClickListener {
            if(binding.edtInputUlasan.text.isNullOrEmpty()) Toast.makeText(this,"Ulasan ini wajib disini!", Toast.LENGTH_SHORT).show()
            else if (ratingValue == 0) Toast.makeText(this, "Silahkan pilih rating terlebih dahulu", Toast.LENGTH_SHORT).show()
            else{
                reviewUlasan = binding.edtInputUlasan.text.toString()
                uploadUlasan(idProduct,sharedPref.getIntData(Constant.ADD_ID_USER),reviewUlasan,ratingValue)
            }
        }

        viewModel.uploadReviewData.observe(this){
            Toast.makeText(this, "Terima kasih anda berhasil memberi ulasan", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

    }

    private fun uploadUlasan(idProduct:Int ,idUser : Int, review : String, rating : Int){
        viewModel.uploadReview(idProduct,idUser,review,rating)
    }
}