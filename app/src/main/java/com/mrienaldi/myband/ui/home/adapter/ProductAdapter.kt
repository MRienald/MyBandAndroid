package com.mrienaldi.myband.ui.home.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mrienaldi.myband.R
import com.mrienaldi.myband.core.data.source.model.ImageData
import com.mrienaldi.myband.core.data.source.model.Product
import com.mrienaldi.myband.databinding.ItemProductBinding
import okhttp3.internal.notify
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("NotifyDataSetChanged")
class ProductAdapter :RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var data = ArrayList<Product>()

    inner class ViewHolder(private val itemBinding: ItemProductBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item:Product, position: Int){
            itemBinding.apply {
                val format: NumberFormat = NumberFormat.getCurrencyInstance()

                format.setMaximumFractionDigits(0)
                format.setCurrency(Currency.getInstance("IDR"))

//                cvProduct.setCardBackgroundColor(Integer.decode("#060930"))
                tvName.text     = item.name
                tvGenre.text    = item.genre
                tvType.text     = item.type
                tvPrice.text    = format.format(item.price)
                imageProduct.setImageResource(item.imageproduct)
            }
        }
    }

    fun addItems(items:List<Product>){
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}