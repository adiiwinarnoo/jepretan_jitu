package com.example.jepretajitu.view.activity.fotographer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jepretajitu.adapter.KatalogAdapter
import com.example.jepretajitu.adapter.LihatKatalogAdapter
import com.example.jepretajitu.databinding.ActivityKatalogFotoBinding
import com.example.jepretajitu.databinding.ActivityLihatKatalogBinding
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.view.activity.user.DetailKatalogActivity
import com.example.jepretajitu.viewmodel.KatalogViewModel

class KatalogFotoActivity : AppCompatActivity() {

    lateinit var binding : ActivityKatalogFotoBinding
    lateinit var lihatKatalogViewModel : KatalogViewModel
    lateinit var adapterKatalog : LihatKatalogAdapter
    lateinit var sharePref : SharedPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKatalogFotoBinding.inflate(layoutInflater)
        lihatKatalogViewModel = ViewModelProvider(this)[KatalogViewModel::class.java]
        sharePref = SharedPrefences(this)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lihatKatalogViewModel.getKatalogById(sharePref.getIntData(Constant.ADD_ID_USER))

        lihatKatalogViewModel.katalogByIdData.observe(this){
            adapterKatalog = LihatKatalogAdapter(it.dataKatalog)
            binding.recyclerView.adapter = adapterKatalog

            adapterKatalog.setItemClickListener(object : LihatKatalogAdapter.ItemClickListener{
                override fun onClick(view: View, position: Int, id : Int, idProduct : Int) {
                    val intent = Intent(this@KatalogFotoActivity, DetailKatalogActivity::class.java)
                    intent.putExtra("ID-USER",id)
                    intent.putExtra("ID-PRODUCT",idProduct)
                    intent.putExtra("FOTOGRAPHER",100)
                    startActivity(intent)
                }
            })
        }
    }
}