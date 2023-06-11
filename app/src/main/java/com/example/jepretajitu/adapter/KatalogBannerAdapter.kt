package com.example.jepretajitu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jepretajitu.R
import com.example.jepretajitu.model.DataKatalogItem2
import com.example.jepretajitu.model.KatalogBanner

class KatalogBannerAdapter (val models : ArrayList<KatalogBanner>) :
    RecyclerView.Adapter<KatalogBannerAdapter.ViewHolder>() {

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val imageSlider = itemView.findViewById<ImageView>(R.id.home_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.katalog_viewpager_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageModel = models[position]
        Glide.with(holder.itemView).load("http://192.168.1.10:8000" + imageModel.fotoUrl).into(holder.imageSlider)
    }

    override fun getItemCount(): Int {
        return models.size
    }
}