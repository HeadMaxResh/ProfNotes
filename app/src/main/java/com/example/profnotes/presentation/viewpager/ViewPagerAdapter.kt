package com.example.profnotes.presentation.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.profnotes.R
import com.google.android.flexbox.FlexboxLayoutManager

class ViewPagerAdapter(
    private val title: List<String>,
    private val number: List<String>,
    private val tagList: List<List<String>>
) :
    RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvItemNumber: TextView = itemView.findViewById(R.id.tv_item_number)
        val tagRecyclerView: RecyclerView = itemView.findViewById(R.id.tag_recycler_view)

        fun bind(title: String, number: String) {
            tvTitle.text = title
            tvItemNumber.text = number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return Pager2ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        val title = title[position]
        val number = number[position]
        holder.bind(title, number)
        holder.tagRecyclerView.layoutManager = FlexboxLayoutManager(holder.itemView.context)
        val adapter = TagAdapter(tagList[position])
        holder.tagRecyclerView.adapter = adapter
    }
}