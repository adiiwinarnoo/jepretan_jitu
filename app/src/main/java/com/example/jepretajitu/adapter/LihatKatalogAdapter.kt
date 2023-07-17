package com.example.jepretajitu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jepretajitu.R
import com.example.jepretajitu.model.DataKatalogItem
import com.example.jepretajitu.model.DataKatalogItem2
import com.example.jepretajitu.model.DataKatalogResponse

class LihatKatalogAdapter (val models : List<DataKatalogItem2?>?) :
    RecyclerView.Adapter<LihatKatalogAdapter.ViewHolder>() {

    private lateinit var itemClickListener : ItemClickListener

    interface ItemClickListener{
        fun onClick(view: View, position: Int, id : Int, idProduct : Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)  {
        var titleBanner = itemView.findViewById<TextView>(R.id.tv_banner)
        var imageBanner = itemView.findViewById<ImageView>(R.id.image_banner)
        var hargaBanner = itemView.findViewById<TextView>(R.id.tv_price_banner)
        var katalogCard = itemView.findViewById<CardView>(R.id.card_katalog)

        fun bind(image : DataKatalogItem2){
            Glide.with(itemView).load("http://adiwinarno.my.id/public"+ image.foto).into(imageBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_katalog, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.katalogCard.setOnClickListener {
            itemClickListener.onClick(it,position,models!![position]!!.idUser!!,models[position]!!.id!!)
        }
        holder.bind(models!![position]!!)
        holder.titleBanner.text = models[position]?.judulProduct
        holder.hargaBanner.text = models[position]?.hargaProduct
    }

    override fun getItemCount(): Int {
        return models!!.size
    }
}