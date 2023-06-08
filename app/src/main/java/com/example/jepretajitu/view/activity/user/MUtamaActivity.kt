package com.example.jepretajitu.view.activity.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jepretajitu.R
import com.example.jepretajitu.databinding.ActivityMutamaBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences

class MUtamaActivity : AppCompatActivity() {

    lateinit var binding : ActivityMutamaBinding
    lateinit var sharedPrefences: SharedPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMutamaBinding.inflate(layoutInflater)
        sharedPrefences = SharedPrefences(this)
        setContentView(binding.root)

        binding.tvFotograpWelcome.text = "${R.string.selamat_datang_kembali} ${sharedPrefences.getStringData(
            Constant.ADD_NAME)}"

        binding.btnUploadKatalog.setOnClickListener {
            start
        }
    }
}