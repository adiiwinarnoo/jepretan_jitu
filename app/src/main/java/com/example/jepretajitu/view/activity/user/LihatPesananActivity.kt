package com.example.jepretajitu.view.activity.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jepretajitu.adapter.KelolaPesananAdapter
import com.example.jepretajitu.adapter.LihatPesananAdapter
import com.example.jepretajitu.databinding.ActivityLihatPesananBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.viewmodel.TransaksiViewModel

class LihatPesananActivity : AppCompatActivity() {

    lateinit var binding : ActivityLihatPesananBinding
    lateinit var viewModel : TransaksiViewModel
    lateinit var sharePreferences : SharedPrefences
    lateinit var adapter : LihatPesananAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLihatPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        sharePreferences = SharedPrefences(this)

        var idUser = sharePreferences.getIntData(Constant.ADD_ID_USER)
        getPayment(idUser)

        viewModel.paymentByIdUser.observe(this){
            if (it.dataPaymentById != null){
                Log.d("DATA-USER-PAY", "onResponse: ${it.toString()}")
                binding.progressBar.visibility = View.GONE
                adapter = LihatPesananAdapter(it.dataPaymentById)
                binding.recyclerView.adapter = adapter
                adapter.setItemClickListener(object : LihatPesananAdapter.ItemClickListener{
                    override fun onEnd(view: View, id: Int) {
                        val status = "pesanan selesai"
                        viewModel.changePayment(id,status)
                        Toast.makeText(this@LihatPesananActivity, "pesanan anda telah selesai.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LihatPesananActivity,MUtamaActivity::class.java))
                    }
                })
            }else{
                adapter = LihatPesananAdapter(emptyList())
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            }
        }
    private fun getPayment(idUser : Int){
        viewModel.getPaymentByIdUser(idUser)
    }
}