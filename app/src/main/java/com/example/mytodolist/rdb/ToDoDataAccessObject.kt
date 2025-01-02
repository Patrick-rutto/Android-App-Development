package com.example.mytodolist.rdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mytodolist.ToDoItems

@Dao                                                          // Marks the interface as a Data Access Object for Room
interface ToDoDataAccessObject {
    @Query("SELECT * FROM ToDoItems")
    fun getAllToDoItems():LiveData<List<ToDoItems>>           // Retrieves All Rows from the ToDoItems Table

    @Insert                                                   // Inserts a single ToDoItems object into the database
    fun addToDoItems(toDoItems: ToDoItems)

    @Query("Delete FROM ToDoItems where todoID = :todoID")    // Deletes a specific ToDoItem from the table based on its todoID
    fun deleteToDoItems(todoID: Int)
}