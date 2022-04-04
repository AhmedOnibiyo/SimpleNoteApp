package com.ahmedonibiyo.simplenoteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmedonibiyo.simplenoteapp.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}