package com.example.firstgithubproject.main_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstgithubproject.databinding.ItemNavBarBinding

class ItemsAdapter (private val items:ArrayList<AdapterModel>, val callback:(Int)->Unit):RecyclerView.Adapter<ItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemNavBarBinding.inflate(LayoutInflater.from(parent.context),null,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            tvBar.text = items[position].title
            ivImage.setImageResource(items[position].image)
            btnNavigate.setOnClickListener {
                callback(position)
            }
        }
    }
}
class ItemViewHolder(val binding: ItemNavBarBinding):RecyclerView.ViewHolder(binding.root){}