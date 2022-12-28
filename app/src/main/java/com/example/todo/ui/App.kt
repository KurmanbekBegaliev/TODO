package com.example.todo.ui

import android.app.Application
import android.content.SharedPreferences
import com.example.todo.data.NoteDatabase
import com.example.todo.ui.utils.Prefs

class App : Application() {

    private lateinit var preferences: SharedPreferences

    companion object {
        lateinit var db : NoteDatabase
        lateinit var prefs: Prefs
    }
    override fun onCreate() {
        super.onCreate()
        preferences = this.applicationContext
            .getSharedPreferences("settings", MODE_PRIVATE)
        prefs = Prefs(preferences)

        db = NoteDatabase.invoke(this)
    }

}