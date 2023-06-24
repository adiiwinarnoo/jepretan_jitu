package com.example.jepretajitu.view.activity.fotographer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jepretajitu.adapter.KelolaPesananAdapter
import com.example.jepretajitu.adapter.PaymentAdapter
import com.example.jepretajitu.databinding.ActivityKelolaPesananBinding
import com.example.jepretajitu.viewmodel.TransaksiViewModel

class KelolaPesananActivity : AppCompatActivity() {

    lateinit var binding : ActivityKelolaPesananBinding
    lateinit var pesananViewModel : TransaksiViewModel
    lateinit var adapter : KelolaPesananAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolaPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pesananViewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        getPayment()

        pesananViewModel.fotoGrapherData.observe(this){
            if (it.dataPaymentFoto != null){
                binding.progressBar.visibility = View.GONE
                adapter = KelolaPesananAdapter(it.dataPaymentFoto)
                binding.recyclerView.adapter = adapter
                adapter.setItemClickListener(object : KelolaPesananAdapter.ItemClickListener{
                    override fun onEnd(view: View, id: Int) {
                        val status = "pesanan selesai"
                        pesananViewModel.changePayment(id,status)
                        Toast.makeText(this@KelolaPesananActivity, "Mohon tunggu sebentar.", Toast.LENGTH_SHORT).show()
                    }
                })
            }else{
                adapter = KelolaPesananAdapter(emptyList())
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
        pesananViewModel.dataUpdateStatus.observe(this){
            getPayment()
        }

    }

    private fun getPayment(){
        pesananViewModel.getFotoGrapherPayment()
    }
}