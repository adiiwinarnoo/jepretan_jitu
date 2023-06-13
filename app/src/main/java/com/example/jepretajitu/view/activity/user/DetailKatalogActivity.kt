package com.example.jepretajitu.view.activity.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.jepretajitu.adapter.KatalogAdapter
import com.example.jepretajitu.adapter.KatalogBannerAdapter
import com.example.jepretajitu.adapter.ReviewAdapter
import com.example.jepretajitu.databinding.ActivityDetailKatalogBinding
import com.example.jepretajitu.model.DataReviewItem
import com.example.jepretajitu.model.KatalogBanner
import com.example.jepretajitu.viewmodel.KatalogViewModel

class DetailKatalogActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailKatalogBinding
    lateinit var lihatKatalogViewModel : KatalogViewModel
    lateinit var adapterKatalog : KatalogBannerAdapter
    lateinit var reviewAdapter : ReviewAdapter
    var listImage = ArrayList<KatalogBanner>()
    var idUser = 0
    var idProduct = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKatalogBinding.inflate(layoutInflater)
        lihatKatalogViewModel = ViewModelProvider(this)[KatalogViewModel::class.java]
        setContentView(binding.root)

        idUser = intent.getIntExtra("ID-USER",0)
        idProduct = intent.getIntExtra("ID-PRODUCT",0)
        binding.rvUlasan.layoutManager = LinearLayoutManager(this)
        lihatKatalogViewModel.getKatalogById(idUser)
        lihatKatalogViewModel.getReviewById(idProduct)

        lihatKatalogViewModel.katalogByIdData.observe(this){
            if (listImage.isNullOrEmpty()){
                listImage.add(KatalogBanner(it.dataKatalog!![0]!!.foto!!))
                listImage.add(KatalogBanner(it.dataKatalog!![0]!!.fotoTwo!!))
                listImage.add(KatalogBanner(it.dataKatalog!![0]!!.fotoThree!!))
            }
            adapterKatalog = KatalogBannerAdapter(listImage)
            binding.viewPagerImage.adapter = adapterKatalog
            binding.viewPagerImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.circleIndicator.setViewPager(binding.viewPagerImage)
            binding.circleIndicator.createIndicators(listImage.size,0)

            binding.tvHargaDetailKatalog.text = "Rp.${it.dataKatalog!![0]!!.hargaProduct}"
            binding.tvJudulKatalog.text = it.dataKatalog[0]!!.judulProduct
            binding.tvValueDeskripsi.text = it.dataKatalog[0]?.deskripsi
            binding.tvDomisili.text = it.dataKatalog[0]?.domisili
            binding.tvNomorWaKatalog.text = it.dataKatalog[0]?.nomorWhatsapp
        }

        lihatKatalogViewModel.reviewData.observe(this){
            if (it.dataReview != null){
                reviewAdapter = ReviewAdapter(it.dataReview)
                binding.rvUlasan.adapter = reviewAdapter
            }
        }
    }
}