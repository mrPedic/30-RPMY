@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.a29_rpmy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun EstablishmentMenu(
    navController: NavController
){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            EstablishmentItem(navController,"Паста Бар", "ул. Пушкина, 15","10:00 - 23:00")
            Spacer(modifier = Modifier.height(8.dp))
            EstablishmentItem(navController,"La Trattoria", "пр. Мира, 42","11:00 - 00:00")

        }
}

@Composable
@Preview(showBackground = true)
fun EstablishmentMenuPreview(){
    EstablishmentMenu(navController = rememberNavController())
}


@Composable
fun EstablishmentItem(
    navController : NavController,
    name: String = "Тестовое название",
    space: String = "Тестовое место",
    timeWork: String = "Тестовое время работы"
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(16.dp))
            .clickable{
                navController.navigate(SealedScreens.EstablishmentDetails.createRoute(name, space,timeWork))
            }
            .padding(12.dp)

    ){
        Text(text = name, color = MaterialTheme.colorScheme.primary)
        Text(text = space, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 15.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun EstablishmentItemPreview(){
    EstablishmentItem(navController = rememberNavController())
}



@Composable
fun EstablishmentDetails(
    navController: NavController,
    name: String = "Тестовое имя",
    space: String = "Тестовое место",
    timeWork: String = "Тестовое время работы"
){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Наименование заведения: $name", color = MaterialTheme.colorScheme.primary)
            Text(text = "Месторасположение: $space", color = MaterialTheme.colorScheme.primary)
            Text(text = "Время работы: $timeWork", color = MaterialTheme.colorScheme.primary)


            Button(onClick = { navController.popBackStack() }) {
                Text("Назад")
            }
        }
}

@Composable
@Preview(showBackground = true)
fun EstablishmentDetailsPreview(){
    EstablishmentDetails(navController = rememberNavController())
}