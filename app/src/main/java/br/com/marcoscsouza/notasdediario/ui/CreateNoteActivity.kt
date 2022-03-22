package br.com.marcoscsouza.notasdediario.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.marcoscsouza.notasdediario.databinding.ActivityCreateNoteBinding
import br.com.marcoscsouza.notasdediario.db.AppDatabase
import br.com.marcoscsouza.notasdediario.db.Note
import br.com.marcoscsouza.notasdediario.db.NoteDao

class CreateNoteActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCreateNoteBinding.inflate(layoutInflater)
    }
    private var noteId = 0L
    private val noteDao: NoteDao by lazy {
        val db = AppDatabase.instance(this)
        db.noteDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Criar nota"

        noteId = intent.getLongExtra("NOTE_ID", 0L)
        createNote()
    }

    override fun onResume() {
        super.onResume()

        updateNote()
    }

    private fun updateNote() {
        noteDao.getById(noteId)?.let {
            title = "Alterar nota"
            binding.titleNoteCreate.setText(it.titleNote)
            binding.bodyNoteCreate.setText(it.bodyNote)
        }
    }

    private fun createNote() {
        binding.btnNoteCreate.setOnClickListener {
            val titleNote = binding.titleNoteCreate.text.toString()
            val bodyNote = binding.bodyNoteCreate.text.toString()

            if (titleNote.isBlank() || bodyNote.isBlank()) {
                Toast.makeText(
                    this,
                    "Preencha todos os campos para criar a nota!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val noteCreated = Note(
                    id = noteId,
                    titleNote = titleNote,
                    dateNote = "TODO Implemented",
                    bodyNote = bodyNote
                )
                noteDao.insert(noteCreated)
                finish()


            }


        }
    }

}