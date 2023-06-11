package com.example.jepretajitu.view.activity.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jepretajitu.adapter.KatalogAdapter
import com.example.jepretajitu.databinding.ActivityLihatKatalogBinding
import com.example.jepretajitu.viewmodel.KatalogViewModel
import com.example.jepretajitu.viewmodel.LoginViewModel

class LihatKatalogActivity : AppCompatActivity() {

    lateinit var binding : ActivityLihatKatalogBinding
    lateinit var lihatKatalogViewModel : KatalogViewModel
    lateinit var adapterKatalog : KatalogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLihatKatalogBinding.inflate(layoutInflater)
        lihatKatalogViewModel = ViewModelProvider(this)[KatalogViewModel::class.java]
        setContentView(binding.root)


        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lihatKatalogViewModel.getKatalogAll()

        lihatKatalogViewModel.katalogData.observe(this){
            adapterKatalog = KatalogAdapter(it.dataKatalog)
            binding.recyclerView.adapter = adapterKatalog
        }


    }
}