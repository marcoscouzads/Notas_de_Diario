package br.com.marcoscsouza.notasdediario.db

import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAll(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg note: Note)

    @Delete
    fun remove(note: Note)

    @Query("SELECT * FROM Note WHERE id = :id")
    fun getById(id: Long): Note?
}