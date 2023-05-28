package com.example.jepretajitu.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.jepretajitu.MainActivity
import com.example.jepretajitu.R
import com.example.jepretajitu.databinding.ActivityLoginBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.view.activity.admin.MenuUtama
import com.example.jepretajitu.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var loginViewModel : LoginViewModel
    var email : String? = null
    var password : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            checkedData()
        }
    }

    private fun checkedData(){
        if (binding.edtEmail.text.toString().isNullOrEmpty()) Toast.makeText(this,
            Constant.EMPTY_EMAIL, Toast.LENGTH_SHORT).show()
        else if (binding.edtPassword.text.toString().isNullOrEmpty()) Toast.makeText(this,
            Constant.EMPTY_PASSWORD, Toast.LENGTH_SHORT).show()
        else{
            email = binding.edtEmail.text.toString()
            password = binding.edtPassword.text.toString()
            loginViewModel = LoginViewModel()
            loginViewModel.login(email!!, password!!).observe(this){
                when (it.message){
                    Constant.SUCCESS_LOGIN -> {
                        when (it.dataLogin!!.idLevel) {
                            1 -> {
                                startActivity(Intent(this, MenuUtama::class.java))
                                finish()
                            }
                            2 -> {
                                Toast.makeText(this, "ini user", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(this, "ini fotografer", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    Constant.WRONG_PASSWORD_EMAIL -> {
                        Toast.makeText(this, Constant.WRONG_PASSWORD_EMAIL, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}