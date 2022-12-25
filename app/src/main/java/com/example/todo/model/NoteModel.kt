package com.example.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "noteModel")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val title : String?= null,
    val description : String?= null
) : Serializable
