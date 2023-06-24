package com.example.jepretajitu.view.activity.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.jepretajitu.adapter.KatalogAdapter
import com.example.jepretajitu.adapter.KatalogBannerAdapter
import com.example.jepretajitu.adapter.ReviewAdapter
import com.example.jepretajitu.databinding.ActivityDetailKatalogBinding
import com.example.jepretajitu.model.DataReviewItem
import com.example.jepretajitu.model.KatalogBanner
import com.example.jepretajitu.utils.Constant
import com.example.jepretajitu.utils.SharedPrefences
import com.example.jepretajitu.viewmodel.KatalogViewModel
import com.example.jepretajitu.viewmodel.TransaksiViewModel

class DetailKatalogActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailKatalogBinding
    lateinit var lihatKatalogViewModel : KatalogViewModel
    lateinit var transaksiViewModel : TransaksiViewModel
    lateinit var adapterKatalog : KatalogBannerAdapter
    lateinit var reviewAdapter : ReviewAdapter
    var listImage = ArrayList<KatalogBanner>()
    var idFotoGrapher = 0
    var idProduct = 0
    lateinit var sharedPref : SharedPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKatalogBinding.inflate(layoutInflater)
        lihatKatalogViewModel = ViewModelProvider(this)[KatalogViewModel::class.java]
        transaksiViewModel = ViewModelProvider(this)[TransaksiViewModel::class.java]
        sharedPref = SharedPrefences(this)
        setContentView(binding.root)

        idFotoGrapher = intent.getIntExtra("ID-USER",0)
        idProduct = intent.getIntExtra("ID-PRODUCT",0)
        binding.rvUlasan.layoutManager = LinearLayoutManager(this)
        lihatKatalogViewModel.getKatalogById(idFotoGrapher)
        lihatKatalogViewModel.getReviewById(idProduct)

        getPayment()

        binding.btnPesan.setOnClickListener {
            val intent = Intent(this,PaymentActivity::class.java)
            intent.putExtra("ID-PRODUCT",idProduct)
            startActivity(intent)
        }

        lihatKatalogViewModel.katalogByIdData.observe(this){
            if (listImage.isNullOrEmpty()){
                listImage.add(KatalogBanner(it.dataKatalog!![0]!!.foto!!))
                listImage.add(KatalogBanner(it.dataKatalog[0]!!.fotoTwo!!))
                listImage.add(KatalogBanner(it.dataKatalog[0]!!.fotoThree!!))
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

        transaksiViewModel.paymentById.observe(this){
            if (it.dataPaymentById != null){
                Log.d("DATA-STATUS", "onCreate: ${it.dataPaymentById[0]?.status}")
                if (it.dataPaymentById[0]!!.status.equals("pesanan selesai")) binding.btnPesan.setText("berikan ulasan")
                if (binding.btnPesan.text.equals("berikan ulasan")){
                    binding.btnPesan.setOnClickListener {
                        startActivity(Intent(this,UlasanActivity::class.java))
                    }
                }
            }
        }
    }

    fun getPayment(){
        transaksiViewModel.getPaymentById(idProduct,sharedPref.getIntData(Constant.ADD_ID_USER))
    }
}