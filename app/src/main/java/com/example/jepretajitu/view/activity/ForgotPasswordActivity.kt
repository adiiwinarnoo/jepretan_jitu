package com.example.jepretajitu.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.jepretajitu.databinding.ActivityForgotPasswordBinding
import com.example.jepretajitu.network.ApiConfig
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.viewmodel.ForgotPasswordViewModel

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var binding : ActivityForgotPasswordBinding
    lateinit var forgotPasswordViewModel: ForgotPasswordViewModel
    var email = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        forgotPasswordViewModel = ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
        setContentView(binding.root)

        binding.btnSimpan.setOnClickListener {
            checkedData()
        }

        forgotPasswordViewModel.forgotPasswordData.observe(this){
            Log.d("FORGOT-PASSWORD", "onCreate: ${it.message}")
            when (it.message){
                Constant.SUCCESS_CHANGE_PASSWORD -> {
                    Toast.makeText(this, "Berhasil ubah password!", Toast.LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                Constant.EMAIL_NOT_REGIST -> {
                    Toast.makeText(this, "Email tidak terdaftar!", Toast.LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE
                }
            }
        }
    }

    private fun checkedData() {
        if (binding.edtEmail.text.isNullOrEmpty()) Toast.makeText(this, "Email Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
        else if (binding.edtPassword.text.isNullOrEmpty()) Toast.makeText(this, "Password Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
        else{
            binding.progressbar.visibility = View.VISIBLE
            email = binding.edtEmail.text.toString()
            password = binding.edtPassword.text.toString()
            forgotPasswordViewModel.sendForgotPassword(email,password)
        }
    }
}