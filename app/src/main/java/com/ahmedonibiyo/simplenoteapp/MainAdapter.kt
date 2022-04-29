package com.ahmedonibiyo.simplenoteapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmedonibiyo.simplenoteapp.data.Note
import com.ahmedonibiyo.simplenoteapp.databinding.RecyclerviewItemBinding

class MainAdapter(
    val context: Context,
    private val noteClickInterface: NoteClickInterface,
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class MainViewHolder(var binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var tvTitle: TextView = binding.tvTitle
        var tvDateStamp: TextView = binding.tvDate
        var cardView = binding.root

    }

    interface NoteClickInterface {
        fun onItemClick(note: Note)
        fun onLongClick(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val note: Note = allNotes[position]
        holder.cardView.setCardBackgroundColor(getRandomColor())

        holder.tvTitle.text = note.noteTitle
        holder.tvDateStamp.text = note.dateStamp

        holder.cardView.setOnClickListener {
            noteClickInterface.onItemClick(allNotes[position])
        }

        holder.cardView.setOnLongClickListener {
            noteClickInterface.onLongClick(allNotes[position])
            true
        }
    }

    // Review: Use color resources instead
    private fun getRandomColor(): Int {
        val colorCode = arrayOf(
            Color.parseColor("#FE9A37"),
            Color.parseColor("#CBDB57"),
            Color.parseColor("#9585BA"),
            Color.parseColor("#5C4F45"),
            Color.parseColor("#F96A4B"),
            Color.parseColor("#DEA44D"),
            Color.parseColor("#9E5C32")
        )

        return colorCode.random()
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    // review: viewModel should be aware of changes
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}