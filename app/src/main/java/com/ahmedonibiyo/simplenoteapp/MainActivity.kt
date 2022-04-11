package com.ahmedonibiyo.simplenoteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.ahmedonibiyo.simplenoteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val adapter: MainAdapter = MainAdapter(NoteList.noteList)

        binding?.recyclerView?.adapter = adapter
        adapter.setOnItemClickListener(object : MainAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    "You clicked on item no. $position",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        binding?.fab?.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}