package com.example.jepretajitu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jepretajitu.R
import com.example.jepretajitu.model.DataPaymentItem
import org.w3c.dom.Text

class PaymentAdapter (val models : List<DataPaymentItem?>) :
    RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    private lateinit var itemClickListener : ItemClickListener

    interface ItemClickListener{
        fun onApprove(view: View, id : Int)
        fun onReject(view: View,id: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {
        var nama = itemView.findViewById<TextView>(R.id.tv_nama_user)
        var tittleProduct = itemView.findViewById<TextView>(R.id.tv_tittle_product)
        var status = itemView.findViewById<TextView>(R.id.tv_status)
        var tanggal = itemView.findViewById<TextView>(R.id.tv_tanggal)
        var approve = itemView.findViewById<TextView>(R.id.btnApprove)
        var reject = itemView.findViewById<TextView>(R.id.tvNoApprove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_approve, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.approve.setOnClickListener {
            itemClickListener.onApprove(it,models[position]!!.id!!)
        }
        holder.reject.setOnClickListener {
            itemClickListener.onReject(it,models[position]!!.id!!)
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