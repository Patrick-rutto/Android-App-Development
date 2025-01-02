package com.example.mytodolist.rdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mytodolist.ToDoItems

@Database(entities = [ToDoItems::class], version = 1)       // Defines entities and version of DB
@TypeConverters(DateConverter::class)                       // Instructs Room on how to Convert Data Types

abstract class ToDoDatabase: RoomDatabase() {

    companion object{
        const val NAME = "MyToDoDB"                         // Name of the database
    }

    abstract fun getToDoDataAccessObject():ToDoDataAccessObject  // Abstract method to get access to the DAO
}