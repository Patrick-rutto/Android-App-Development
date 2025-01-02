package com.example.mytodolist

import androidx.compose.runtime.Composable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity                                       // Gets/set Field Values From/To Room Database
data class ToDoItems(                         // To hold Our Entities in a Structured Way
    @PrimaryKey(autoGenerate = true)          // Sets todoID as the Primary Key
    var todoID: Int = 0,
    var todoItem : String,
    var timeCreated : Date
)



