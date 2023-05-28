package com.example.jepretajitu.view.activity.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jepretajitu.databinding.ActivityMenuUtamaBinding

class MenuUtama : AppCompatActivity() {

    lateinit var binding : ActivityMenuUtamaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuUtamaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}