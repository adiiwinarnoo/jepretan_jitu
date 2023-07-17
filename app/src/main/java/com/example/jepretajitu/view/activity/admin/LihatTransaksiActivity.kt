package com.example.jepretajitu.view.activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jepretajitu.adapter.LihatPesananAdapter
import com.example.jepretajitu.adapter.LihatTransaksiAdapter
import com.example.jepretajitu.databinding.ActivityLihatTransaksiBinding
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.view.activity.user.MUtamaActivity
import com.example.jepretajitu.viewmodel.TransaksiViewModel

class LihatTransaksiActivity : AppCompatActivity() {

    lateinit var binding : ActivityLihatTransaksiBinding
    lateinit var viewModel : TransaksiViewModel
    lateinit var sharePreferences : SharedPrefences
    lateinit var adapter : LihatTransaksiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLihatTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        sharePreferences = SharedPrefences(this)

        getAllTransaksi()


        viewModel.adminData.observe(this){
            if (it.lihatTransaksiAdmin != null){
                Log.d("DATA-USER-PAY", "onResponse: ${it.toString()}")
                binding.progressBar.visibility = View.GONE
                adapter = LihatTransaksiAdapter(it.lihatTransaksiAdmin)
                binding.recyclerView.adapter = adapter
            }else{
                adapter = LihatTransaksiAdapter(emptyList())
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }


    }
    private fun getAllTransaksi(){
        viewModel.getAllTransaksi()
    }
}