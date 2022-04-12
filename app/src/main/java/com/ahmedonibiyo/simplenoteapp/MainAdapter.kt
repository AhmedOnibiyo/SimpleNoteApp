package com.ahmedonibiyo.simplenoteapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedonibiyo.simplenoteapp.databinding.RecyclerviewItemBinding

class MainAdapter(
    val context: Context,
    private val noteClickInterface: NoteClickInterface,
    private val deleteIconClickInterface: DeleteIconClickInterface
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val allNotes = ArrayList<Note>()

    interface NoteClickInterface {
        fun onNoteClick(note: Note)
    }

    interface DeleteIconClickInterface {
        fun onDeleteIconClick(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MainViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemBinding.tvTitle.text = allNotes[position].noteTitle
        holder.itemBinding.tvTimeStamp.text = "Last Updated: " + allNotes[position].timestamp

        holder.itemBinding.ivDelete.setOnClickListener {
            deleteIconClickInterface.onDeleteIconClick(allNotes[position])
        }

        holder.itemBinding.root.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(
        var itemBinding: RecyclerviewItemBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }
}