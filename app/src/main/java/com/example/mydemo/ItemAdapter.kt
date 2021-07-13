package com.example.mydemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemList:List<ReItem>) :RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.imgView.setImageResource(currentItem.imgResource)
        holder.t1View.text= currentItem.title1
        holder.t2View.text= currentItem.title2
    }

    override fun getItemCount() = itemList.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imgView:ImageView = itemView.findViewById(R.id.i_img)
        val t1View:TextView = itemView.findViewById(R.id.i_txt1)
        val t2View:TextView = itemView.findViewById(R.id.i_txt2)

    }
}