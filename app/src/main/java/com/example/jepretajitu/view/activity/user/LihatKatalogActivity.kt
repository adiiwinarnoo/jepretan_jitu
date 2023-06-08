package com.example.jepretajitu.view.activity.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jepretajitu.databinding.ActivityLihatKatalogBinding

class LihatKatalogActivity : AppCompatActivity() {

    lateinit var binding : ActivityLihatKatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLihatKatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}