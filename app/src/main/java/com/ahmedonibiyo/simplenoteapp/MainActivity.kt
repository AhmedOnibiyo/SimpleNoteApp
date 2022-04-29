package com.ahmedonibiyo.simplenoteapp

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ahmedonibiyo.simplenoteapp.data.Note
import com.ahmedonibiyo.simplenoteapp.databinding.ActivityMainBinding
import com.ahmedonibiyo.simplenoteapp.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), MainAdapter.NoteClickInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(this, this) }

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

        setSupportActionBar(binding.toolbar)
    }

    override fun onItemClick(note: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteDateStamp", note.dateStamp)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onLongClick(note: Note) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Note")
        builder.setMessage("Do you really want to delete the note ?")
        builder.setIcon(R.drawable.ic_delete)

        builder.setPositiveButton("Yes") { dialog, _ ->
            viewModel.deleteNote(note)
            Toast.makeText(this@MainActivity, "${note.noteTitle} Deleted", Toast.LENGTH_SHORT)
                .show()
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.menu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchDatabase(query)
                }

                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchDatabase(query)
                }

                return true
            }
        })

        return true
    }

    internal fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery).observe(this) { list ->
            list.let {
                adapter.updateList(it)
            }
        }
    }
}