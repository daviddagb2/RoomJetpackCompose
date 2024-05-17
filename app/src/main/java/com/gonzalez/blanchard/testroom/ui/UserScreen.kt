package com.gonzalez.blanchard.testroom.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gonzalez.blanchard.testroom.viewmodel.UserViewModel

@Composable
fun UserScreen(

) {
    val userViewModel: UserViewModel = viewModel()
    val users by userViewModel.users.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        var userName by remember { mutableStateOf("") }
        var userAge by remember { mutableStateOf("") }

        Text("Listado de Usuarios", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userAge,
            onValueChange = { userAge = it },
            label = { Text("Age") }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if(userName.isNotEmpty() && userAge.isNotEmpty()){
                    userViewModel.insertUser(userName, userAge.toInt())
                    userName = ""
                    userAge = ""
                }
            }
        ){
            Text("Agregar")
        }
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            for (user in users) {
                item{
                    Text("Name: ${user.name}, Age: ${user.age}")
                    Divider()
                }
            }
        }

    }



}