package com.example.jepretajitu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jepretajitu.R
import com.example.jepretajitu.model.DataPaymentFotoItem

class KelolaPesananAdapter (val models : List<DataPaymentFotoItem?>) :
    RecyclerView.Adapter<KelolaPesananAdapter.ViewHolder>() {

    private lateinit var itemClickListener : ItemClickListener
    private lateinit var itemClickListener2 : ItemClickListener2

    interface ItemClickListener{
        fun onEnd(view: View, id : Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }
    interface ItemClickListener2{
        fun onReject(view: View, id : Int)
    }

    fun setItemClickListener2(itemClickListener2: ItemClickListener2){
        this.itemClickListener2 = itemClickListener2
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        var nama = itemView.findViewById<TextView>(R.id.tv_nama_user)
        var tittleProduct = itemView.findViewById<TextView>(R.id.tv_tittle_product)
        var status = itemView.findViewById<TextView>(R.id.tv_status)
        var tanggal = itemView.findViewById<TextView>(R.id.tv_tanggal)
        var btnApprove = itemView.findViewById<TextView>(R.id.btnApprove)
        var btnReject = itemView.findViewById<TextView>(R.id.btnReject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fotographer, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btnApprove.setOnClickListener {
            itemClickListener.onEnd(it,models[position]!!.id!!)
        }
        holder.btnReject.setOnClickListener {
            itemClickListener2.onReject(it,models[position]!!.id!!)
        }
        holder.nama.text = models[position]?.nama
        holder.tittleProduct.text = models[position]?.judulProduct
        holder.status.text = models[position]?.status
        holder.tanggal.text = models[position]?.tanggal

    }

    override fun getItemCount(): Int {
        return models.size
    }
}