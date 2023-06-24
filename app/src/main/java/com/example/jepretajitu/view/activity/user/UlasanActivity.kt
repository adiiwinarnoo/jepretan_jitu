package com.example.jepretajitu.view.activity.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jepretajitu.databinding.ActivityUlasanBinding

class UlasanActivity : AppCompatActivity() {

    lateinit var binding : ActivityUlasanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUlasanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}