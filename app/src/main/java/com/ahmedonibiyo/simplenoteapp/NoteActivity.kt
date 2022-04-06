package com.ahmedonibiyo.simplenoteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ahmedonibiyo.simplenoteapp.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backIcon.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        binding.editNote.setOnClickListener{
            Toast.makeText(this,
                "Keep calm we shall implement that feature soon...",
                Toast.LENGTH_SHORT).show()
        }
    }
}