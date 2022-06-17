package com.mrienaldi.myband.ui.catagory.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrienaldi.myband.core.data.source.model.Catagory
import com.mrienaldi.myband.databinding.ItemCatagoryBinding
import com.mrienaldi.myband.databinding.ItemProductBinding
import kotlin.collections.ArrayList

@SuppressLint("NotifyDataSetChanged")
class CatagoryAdapter :RecyclerView.Adapter<CatagoryAdapter.ViewHolder>() {

    private var data = ArrayList<Catagory>()

    inner class ViewHolder(private val itemBinding: ItemCatagoryBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item:Catagory, position: Int){
            itemBinding.apply {

                cvCatagory.setCardBackgroundColor(Color.parseColor(item.card_color))
                tvCatagory.text     = item.name_catagory
                imageCatagory.setImageResource(item.imagecatagory)

            }
        }
    }

    fun addItems(items:List<Catagory>){
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCatagoryBinding.inflate(
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