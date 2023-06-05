package com.example.jepretajitu.view.activity.fotographer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jepretajitu.databinding.ActivityUploadKatalogBinding

class UploadKatalogActivity : AppCompatActivity() {

    lateinit var binding : ActivityUploadKatalogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadKatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}