package com.example.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.model.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteModel ORDER BY id DESC")
    fun getAllNote() : List<NoteModel>

    @Query("SELECT * FROM noteModel ORDER BY id")
    fun getAllNoteDesc() : List<NoteModel>

    @Query("SELECT * FROM noteModel ORDER BY title")
    fun getAllNoteSortedAlp() : List<NoteModel>

    @Query("SELECT * FROM noteModel ORDER BY title DESC")
    fun getAllNoteSortedAlpDesc() : List<NoteModel>

    @Insert
    fun addNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)

    @Update
    fun upDateNote(model: NoteModel)
}