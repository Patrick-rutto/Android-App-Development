package com.example.mytodolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ToDoPlanViewModel: ViewModel() {                                 // ViewModel for managing the ToDoList functionality.

    val toDoDataAccessObject = DbMainApplication.toDoDatabase
        .getToDoDataAccessObject()                                     // Accessing DAO


    val todoList : LiveData<List<ToDoItems>> = toDoDataAccessObject
        .getAllToDoItems()                                             // LiveData to observe the list of ToDoItems from the database


    fun addToDos(todoItem : String){                                   // Adds a new ToDoItem to the database.

        viewModelScope.launch(Dispatchers.IO) {
            toDoDataAccessObject.addToDoItems(ToDoItems(todoItem = todoItem,
                timeCreated = Date.from(Instant.now())))
        }

    }

    fun deleteToDos(todoID : Int){                                    // Deletes ToDoItem from the database.

        viewModelScope.launch(Dispatchers.IO) {
            toDoDataAccessObject.deleteToDoItems(todoID)
        }
    }
}