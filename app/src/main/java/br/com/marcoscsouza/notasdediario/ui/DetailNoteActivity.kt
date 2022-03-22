package br.com.marcoscsouza.notasdediario.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.marcoscsouza.notasdediario.R
import br.com.marcoscsouza.notasdediario.databinding.ActivityDetailNoteBinding
import br.com.marcoscsouza.notasdediario.db.AppDatabase
import br.com.marcoscsouza.notasdediario.db.Note

class DetailNoteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailNoteBinding.inflate(layoutInflater)
    }
    private var noteId: Long = 0L
    private var note: Note? = null
    private val noteDao by lazy {
        AppDatabase.instance(this).noteDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Nota aberta"
        noteId = intent.getLongExtra("NOTE_ID", 0L)


    }

    override fun onResume() {
        super.onResume()

        note = noteDao.getById(noteId)
        note?.let {
            with(binding) {
                txtTitleDetailNote.text = it.titleNote
                txtDateDetailNote.text = "TODO Implemented"
                txtBodyDetailNote.text = it.bodyNote
            }
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notemenu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.edit_menu -> {
                val i = Intent(this, CreateNoteActivity::class.java).apply {
                    putExtra("NOTE_ID", noteId)
                }
                startActivity(i)
            }
            R.id.delete_menu -> {
                note?.let { noteDao.remove(it) }
                finish()
                Toast.makeText(this, "Nota apagada!", Toast.LENGTH_SHORT).show()

            }
        }
        return super.onOptionsItemSelected(item)
    }

}