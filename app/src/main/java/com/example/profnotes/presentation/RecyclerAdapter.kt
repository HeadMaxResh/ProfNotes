package com.example.profnotes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.profnotes.databinding.ItemBinding

class RecyclerAdapter(private val items: List<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvItem.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}