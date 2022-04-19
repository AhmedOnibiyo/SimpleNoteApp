package com.ahmedonibiyo.simplenoteapp

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ahmedonibiyo.simplenoteapp.data.Note
import com.ahmedonibiyo.simplenoteapp.viewmodel.NoteViewModel

class SearchableActivity : AppCompatActivity() {


    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?. also { query ->
                searchDatabase(query)
            }
        }
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

//        viewModel.searchDatabase(searchQuery).observe(this) { list ->
//            list.let {
//                adapter.updateList(it)
//            }
//        }
    }

}