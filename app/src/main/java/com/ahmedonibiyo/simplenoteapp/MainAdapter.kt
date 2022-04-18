package com.ahmedonibiyo.simplenoteapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmedonibiyo.simplenoteapp.data.Note
import com.ahmedonibiyo.simplenoteapp.databinding.RecyclerviewItemBinding
import java.util.*

class MainAdapter(
    val context: Context,
    private val noteClickInterface: NoteClickInterface,
    private val deleteIconClickInterface: DeleteIconClickInterface
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class MainViewHolder(
        var binding: RecyclerviewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        var tvTitle: TextView = binding.tvTitle
        var tvTimeStamp: TextView = binding.tvTimeStamp
        var ivDelete = binding.ivDelete
    }

    interface NoteClickInterface {
        fun onNoteClick(note: Note)
    }

    interface DeleteIconClickInterface {
        fun onDeleteIconClick(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.tvTitle.text = allNotes[position].noteTitle
        holder.tvTimeStamp.text = "Last Updated: " + allNotes[position].timestamp

        holder.ivDelete.setOnClickListener {
            deleteIconClickInterface.onDeleteIconClick(allNotes[position])
        }

        holder.binding.root.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    private fun getRandomColor(): Int {
        var colorCode: MutableList<Int> = ArrayList<Int>()

        colorCode.add(R.color.app_color1)
        colorCode.add(R.color.app_color2)
        colorCode.add(R.color.app_color3)
        colorCode.add(R.color.app_color4)
        colorCode.add(R.color.app_color5)
        colorCode.add(R.color.app_color6)
        colorCode.add(R.color.app_color7)

        return Random().nextInt(colorCode.size)

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}