package com.example.jepretajitu.view.activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jepretajitu.databinding.ActivityMenuUtamaBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.view.activity.LoginActivity

class MenuUtama : AppCompatActivity() {

    lateinit var binding : ActivityMenuUtamaBinding
    lateinit var sharePreferences : SharedPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuUtamaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharePreferences = SharedPrefences(this)

        binding.btnApprovePembayaran.setOnClickListener {
            startActivity(Intent(this,ApprovePaymentActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            sharePreferences.putIntData(Constant.AFTER_LOGIN,0)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}