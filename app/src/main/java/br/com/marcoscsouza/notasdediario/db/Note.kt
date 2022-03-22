package br.com.marcoscsouza.notasdediario.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "title_note") val titleNote: String,
    @ColumnInfo(name = "date_note") val dateNote: String,
    @ColumnInfo(name = "body_note") val bodyNote: String,
) : Parcelable