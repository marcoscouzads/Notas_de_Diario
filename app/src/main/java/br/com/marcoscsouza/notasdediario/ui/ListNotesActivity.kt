package br.com.marcoscsouza.notasdediario.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.marcoscsouza.notasdediario.adapter.NoteAdapter
import br.com.marcoscsouza.notasdediario.databinding.ActivityListNotesBinding
import br.com.marcoscsouza.notasdediario.db.AppDatabase

class ListNotesActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityListNotesBinding.inflate(layoutInflater)
    }
    private val adapter = NoteAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Lista de notas"

        configRV()

        configFAB()

    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.instance(this)
        val noteDao = db.noteDao()
        adapter.update(noteDao.getAll())

    }

    private fun configFAB() {
        val fab = binding.fabCreateNote
        fab.setOnClickListener {
            val i = Intent(this, CreateNoteActivity::class.java)
            startActivity(i)
        }
    }

    private fun configRV() {
        val rv = binding.rvNoteList
        rv.adapter = adapter
        adapter.clickOnItem = {
            val i = Intent(this, DetailNoteActivity::class.java)
                .apply {
                    //                    can use a class Constant
                    putExtra("NOTE_ID", it.id)
                }
            startActivity(i)

        }
    }

}