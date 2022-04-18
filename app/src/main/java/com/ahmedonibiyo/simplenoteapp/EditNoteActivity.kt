package com.ahmedonibiyo.simplenoteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ahmedonibiyo.simplenoteapp.data.Note
import com.ahmedonibiyo.simplenoteapp.databinding.ActivityEditNoteBinding
import com.ahmedonibiyo.simplenoteapp.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditNoteBinding
    lateinit var viewModel: NoteViewModel
    var noteID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID", 0)
            binding.saveBtn.text = getString(R.string.update_note)
            binding.etNoteTitle.setText(noteTitle)
            binding.etNoteDescription.setText(noteDesc)
        } else {
            binding.saveBtn.text = "Save Note"
        }

        binding.saveBtn.setOnClickListener {
            val noteTitle = binding.etNoteTitle.text.toString()
            val noteDesc = binding.etNoteDescription.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                    val sdf = SimpleDateFormat("MMMM dd, yyyy - HH:mm", Locale.getDefault())
                    val currentDate = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDesc, currentDate)
                    updateNote.id = noteID

                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated...", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                    val sdf = SimpleDateFormat("MMMM dd, yyyy - HH:mm", Locale.getDefault())
                    val currentDate = sdf.format(Date())

                    viewModel.addNote(Note(noteTitle, noteDesc, currentDate))
                    Toast.makeText(this, "Note Added..", Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

        binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}