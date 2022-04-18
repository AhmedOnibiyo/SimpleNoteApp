package com.ahmedonibiyo.simplenoteapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ahmedonibiyo.simplenoteapp.data.Note
import com.ahmedonibiyo.simplenoteapp.databinding.ActivityMainBinding
import com.ahmedonibiyo.simplenoteapp.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), MainAdapter.NoteClickInterface,
    MainAdapter.DeleteIconClickInterface, SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(this, this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteTimeStamp", note.timestamp)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this@MainActivity, "${note.noteTitle} Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu.findItem(R.menu.main_menu)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
       if (query != null) {
           searchDatabase(query)
       }

        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery).observe(this) { list ->
            list.let {
                adapter.updateList(it)
            }
        }
    }
}