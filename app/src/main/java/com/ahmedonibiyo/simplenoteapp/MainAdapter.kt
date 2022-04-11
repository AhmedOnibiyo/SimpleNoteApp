package com.ahmedonibiyo.simplenoteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedonibiyo.simplenoteapp.databinding.RecyclerviewItemBinding

class MainAdapter(val noteList: List<Note>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MainViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val note = noteList[position]
        holder.bindItem(note)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    inner class MainViewHolder(
        var itemBinding: RecyclerviewItemBinding,
        listener: onItemClickListener
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(note: Note) {
            itemBinding.titleTv.text = note.title
            itemBinding.dateTv.text = note.date.toString()
        }

        init {
            itemBinding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

}