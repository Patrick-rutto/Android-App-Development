package com.example.mytodolist


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable

fun MyToDoListPage(viewModel: ToDoPlanViewModel){
    val todoList by viewModel.todoList.observeAsState()               // To Automatically Update the UI when the LiveData Value Changes
    var addText by remember{ mutableStateOf("")                 // Remember Function to Retain the State Across UI Recompositions
    }

    Column (
        modifier = Modifier.fillMaxHeight()
            .padding(10.dp, 80.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = addText,
                onValueChange = {addText = it                          // To take the Input From the User in the addText
            })
            Button(onClick = {
                viewModel.addToDos(addText)                            // Functionality for Adding Items To Our List
                addText = ""
            }) {
                Text(text = "Add")
            }
        }

        todoList?.let{
            LazyColumn (                                               // LazyColumn To Display Our Scrollable List of Todos
                content = {
                    itemsIndexed(it) {index: Int, item: ToDoItems ->
                        Items(item = item, onDelete = {viewModel
                            .deleteToDos(item.todoID)})
                    }
                }
            )

        }?: Text(                                                      // To Display Text When No Items Have Been Added
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Add Items here",
            fontSize = 16.sp
        )
    }

}
@Composable                                                                       // Composable For Adding Our TodoItems
fun Items(item: ToDoItems, onDelete : ()->Unit){
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.LightGray)
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically

    ){
        Column(modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/MM/yy",Locale.ENGLISH).format(item.timeCreated),
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = item.todoItem,
                fontSize = 18.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {                                       // Adding Delete Functionality
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24), // Adding a Delete Icon
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}