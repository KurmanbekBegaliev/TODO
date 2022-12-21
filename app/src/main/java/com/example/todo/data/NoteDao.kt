package com.example.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.model.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM notemodel")
    fun getAllNote() : List<NoteModel>

    @Insert
    fun addNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)
}