package com.example.jepretajitu.view.activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jepretajitu.adapter.PaymentAdapter
import com.example.jepretajitu.databinding.ActivityApprovePaymentBinding
import com.example.jepretajitu.viewmodel.TransaksiViewModel

class ApprovePaymentActivity : AppCompatActivity() {

    lateinit var binding : ActivityApprovePaymentBinding
    lateinit var viewModel : TransaksiViewModel
    lateinit var adapter : PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApprovePaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        getPayment()

        viewModel.dataUpdateStatus.observe(this){
            getPayment()
        }

        viewModel.dataGetPayment.observe(this){
            Log.d("IT-DATA", "getPayment: ${it.dataPayment}")
            if (it.dataPayment != null) {
                binding.progressBar.visibility = View.GONE
                adapter = PaymentAdapter(it.dataPayment)
                binding.recyclerView.adapter = adapter
                adapter.setItemClickListener(object : PaymentAdapter.ItemClickListener{
                    override fun onApprove(view: View, id: Int) {
                        val status = "pembayaran berhasil"
                        viewModel.changePayment(id,status)
                        Toast.makeText(this@ApprovePaymentActivity, "approved!", Toast.LENGTH_SHORT).show()
                    }
                    override fun onReject(view: View, id: Int) {
                        val status = "pembayaran gagal"
                        viewModel.changePayment(id,status)
                        Toast.makeText(this@ApprovePaymentActivity, "rejected!", Toast.LENGTH_SHORT).show()
                    }
                })
                adapter.notifyDataSetChanged()
            }else{
                adapter = PaymentAdapter(emptyList())
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun getPayment(){
        viewModel.getPayment()
    }
}