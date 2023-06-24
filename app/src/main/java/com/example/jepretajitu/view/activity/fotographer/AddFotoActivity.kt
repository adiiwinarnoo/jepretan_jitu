package com.example.jepretajitu.view.activity.fotographer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jepretajitu.R
import com.example.jepretajitu.databinding.ActivityAddFotoBinding
import com.example.jepretajitu.databinding.ActivityLoginBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences

class AddFotoActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddFotoBinding
    lateinit var sharedPrefences: SharedPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFotoBinding.inflate(layoutInflater)
        sharedPrefences = SharedPrefences(this)
        setContentView(binding.root)

        binding.tvFotograpWelcome.text = "${R.string.selamat_datang_kembali} ${sharedPrefences.getStringData(Constant.ADD_NAME)}"

        binding.btnUploadKatalog.setOnClickListener {
            startActivity(Intent(this, UploadKatalogActivity::class.java))
            finish()
        }

        binding.btnLihatPesanan.setOnClickListener {
            startActivity(Intent(this, KelolaPesananActivity::class.java))
        }
    }
}