@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.a29_rpmy

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun PizzaMenu(
    navController: NavController
){

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            PizzaItem(navController, "99 сыра", "Пицца созданная богами для богов")
            Spacer(modifier = Modifier.height(8.dp))
            PizzaItem(navController, "4 сезона", "4 сезона покказывают скоротечность времени")
        }
    }

@Composable
@Preview(showBackground = true)
fun PizzaMenuPreview(){
    PizzaMenu(navController = rememberNavController())
}



@Composable
fun PizzaItem(
    navController: NavController,
    name: String = "Тестовое название",
    description: String = "Тестовое описание"
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(16.dp))
            .clickable{
                navController.navigate(SealedScreens.PizzaDetails.createRoute(name,description))
            }
            .padding(12.dp)

    ){
        Text(text = name, color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
@Preview(showBackground = true)
fun PizzaItemPreview (){
    PizzaItem(navController = rememberNavController())
}

@Composable
fun PizzaDetails(
    navController: NavController,
    name: String = "Тестовое название",
    description: String = "Тестовое описание"
){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Наименование пиццы: $name", color = MaterialTheme.colorScheme.primary)
            Text(text = "Описание: $description", color = MaterialTheme.colorScheme.primary)

            Button(onClick = { navController.popBackStack() }) {
                Text("Назад")
            }
        }
    }


@Composable
@Preview(showBackground = true)
fun PizzaDetailsPreview(){
    PizzaDetails(navController = rememberNavController())
}