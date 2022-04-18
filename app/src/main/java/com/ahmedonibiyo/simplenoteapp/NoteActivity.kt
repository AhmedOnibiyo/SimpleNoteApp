package com.ahmedonibiyo.simplenoteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ahmedonibiyo.simplenoteapp.databinding.ActivityNoteBinding
import com.ahmedonibiyo.simplenoteapp.viewmodel.NoteViewModel

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private lateinit var viewModel: NoteViewModel
    private var noteID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            val noteTimeStamp = intent.getStringExtra("noteTimeStamp")
            noteID = intent.getIntExtra("noteID", 0)
            binding.tvNoteTitle.text = noteTitle
            binding.tvNoteDesc.text = noteDesc
            binding.tvTimeStamp.text = noteTimeStamp
        }

        binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.editBtn.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            intent.putExtra("noteType", "Edit")
            intent.putExtra("noteTitle", binding.tvNoteTitle.text)
            intent.putExtra("noteDescription", binding.tvNoteDesc.text)
            intent.putExtra("noteTimeStamp", binding.tvTimeStamp.text)
            startActivity(intent)

//            Toast.makeText(
//                this,
//                "Keep calm we shall implement that feature soon...",
//                Toast.LENGTH_SHORT
//            ).show()
        }

    }
}