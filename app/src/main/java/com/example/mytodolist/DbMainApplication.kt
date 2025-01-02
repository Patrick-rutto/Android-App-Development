package com.example.mytodolist

import android.app.Application
import androidx.room.Room
import com.example.mytodolist.rdb.ToDoDatabase

class DbMainApplication : Application() {                 // Main Application For initializing the Room database

    companion object {
        lateinit var toDoDatabase: ToDoDatabase
    }

    override fun onCreate() {                             // Initializing the Database Instance
        super.onCreate()
        toDoDatabase = Room.databaseBuilder(              // Building the Room database instance
            applicationContext,
            ToDoDatabase::class.java,
            ToDoDatabase.NAME
        ).build()
    }
}