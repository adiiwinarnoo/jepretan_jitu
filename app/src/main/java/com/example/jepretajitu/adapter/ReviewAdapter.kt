package com.example.jepretajitu.adapter

import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jepretajitu.R
import com.example.jepretajitu.model.DataReviewItem
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class ReviewAdapter (val models : List<DataReviewItem?>?) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {
        var userName = itemView.findViewById<TextView>(R.id.username)
        var imgProfil = itemView.findViewById<CircleImageView>(R.id.image_profile_review)
        var review = itemView.findViewById<TextView>(R.id.tv_review)
        var ratingBar = itemView.findViewById<RatingBar>(R.id.ratingbar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userName.text = models!![position]?.namaUser
        holder.review.text = models[position]?.review
        Glide.with(holder.itemView).load("http://192.168.1.10:8000" + models[position]?.foto).into(holder.imgProfil)
        holder.ratingBar.rating = models[position]?.rating!!.toFloat()

    }

    override fun getItemCount(): Int {
        return models!!.size
    }
}