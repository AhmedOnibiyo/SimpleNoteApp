package com.ahmedonibiyo.simplenoteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedonibiyo.simplenoteapp.databinding.RecyclerviewItemBinding

class MainAdapter(val noteList: List<Note>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(var itemBinding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(note: Note) {
            itemBinding.titleTv.text = note.title
            itemBinding.dateTv.text = note.date.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val note = noteList[position]
        holder.bindItem(note)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

}