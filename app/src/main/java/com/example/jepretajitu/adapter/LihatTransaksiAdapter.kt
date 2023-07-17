package com.example.jepretajitu.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jepretajitu.R
import com.example.jepretajitu.model.DataPaymentByIdItem
import com.example.jepretajitu.model.DataPaymentFotoItem
import com.example.jepretajitu.model.LihatTransaksiAdminItem

class LihatTransaksiAdapter (val models : List<LihatTransaksiAdminItem?>) :
    RecyclerView.Adapter<LihatTransaksiAdapter.ViewHolder>() {

    private lateinit var itemClickListener : ItemClickListener

    interface ItemClickListener{
        fun onEnd(view: View, id : Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        var nama = itemView.findViewById<TextView>(R.id.tv_nama_user)
        var tittleProduct = itemView.findViewById<TextView>(R.id.tv_tittle_product)
        var status = itemView.findViewById<TextView>(R.id.tv_status)
        var tanggal = itemView.findViewById<TextView>(R.id.tv_tanggal)
        var nomorHp = itemView.findViewById<TextView>(R.id.value_nomor_foto)
        var alamat = itemView.findViewById<TextView>(R.id.value_alamat_foto)
        var btnPesananBerakhir = itemView.findViewById<TextView>(R.id.btnApprove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lihat_transaksi_admin, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btnPesananBerakhir.setOnClickListener {
            itemClickListener.onEnd(it,models[position]!!.id!!)
        }
        if (models[position]?.status.equals("pesanan ditolak")){
            holder.status.setTextColor(Color.RED)
        }
        holder.nama.text = models[position]?.nama
        holder.tittleProduct.text = models[position]?.judulProduct
        holder.status.text = models[position]?.status
        holder.tanggal.text = models[position]?.tanggal
        holder.nomorHp.text = models[position]?.nomorWhatsapp
        holder.alamat.text = models[position]?.domisili



    }

    override fun getItemCount(): Int {
        return models.size
    }
}