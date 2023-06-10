package com.example.jepretajitu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jepretajitu.R
import com.example.jepretajitu.model.DataKatalogItem
import com.example.jepretajitu.model.DataKatalogResponse

class KatalogAdapter (val models : List<DataKatalogItem?>?) :
    RecyclerView.Adapter<KatalogAdapter.ViewHolder>() {


    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)  {
        var titleBanner = itemView.findViewById<TextView>(R.id.tv_banner)
        var imageBanner = itemView.findViewById<ImageView>(R.id.image_banner)
        var hargaBanner = itemView.findViewById<TextView>(R.id.tv_price_banner)

        fun bind(image : DataKatalogItem){
            Glide.with(itemView).load("http://192.168.1.10:8000"+ image.foto).into(imageBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_katalog, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(models!![position]!!)
        holder.titleBanner.text = models[position]?.judulProduct
        holder.hargaBanner.text = models[position]?.hargaProduct
    }

    override fun getItemCount(): Int {
        return models!!.size
    }
}