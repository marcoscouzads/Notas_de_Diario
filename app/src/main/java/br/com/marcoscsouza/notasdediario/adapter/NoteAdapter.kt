package br.com.marcoscsouza.notasdediario.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.marcoscsouza.notasdediario.databinding.NoteListItemBinding
import br.com.marcoscsouza.notasdediario.db.Note

class NoteAdapter(
    private val context: Context,
    notes : List<Note> = emptyList(),
    var clickOnItem: (note: Note) -> Unit = {}
): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val notes = notes.toMutableList()

    inner class ViewHolder(private val binding: NoteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var note: Note

        init {
            itemView.setOnClickListener {
                if (::note.isInitialized) {
                    clickOnItem(note)
                }
            }
        }

        fun bind(note: Note) {
            this.note = note
            val title = binding.titleItemNote
            title.text = note.titleNote

            val date = binding.dateItemNote
            date.text = note.dateNote

            val body = binding.bodyItemNote
            body.text = note.bodyNote

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = NoteListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note = note)
    }

    override fun getItemCount(): Int = notes.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(notes: List<Note>){
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }
}