package com.example.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.model.NoteModel

@Database(entities = [NoteModel::class], version = 2)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE : NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "DB NAME"
            ).allowMainThreadQueries().build()
    }

}