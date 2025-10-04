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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PastaMenu(
    navController: NavController
){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            PastaItem(navController, "карбонаро", "Классическая итальянская паста с беконом, яйцами, сыром пармезан и черным перцем")
            Spacer(modifier = Modifier.height(8.dp))
            PastaItem(navController, "Болоньезе","Паста с мясным соусом из говядины, томатов, лука, моркови и сельдерея")
        }
    }

@Composable
@Preview(showBackground = true)
fun PastaMenuPreview(){
    PastaMenu(navController = rememberNavController())
}

@Composable
fun PastaItem(
    navController: NavController,
    name: String = "Тестовое название",
    description: String = "Тестовое описание"
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(16.dp))
            .clickable{
                navController.navigate(SealedScreens.PastaDetails.createRoute(name,description))
            }
            .padding(12.dp)

    ){
        Text(text = name, color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
@Preview(showBackground = true)
fun PastaItemPreview(){
    PastaItem(navController = rememberNavController())
}





@Composable
fun PastaDetails(
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
            Text(text = "Наименование пасты: $name", color = MaterialTheme.colorScheme.primary)
            Text(text = "Описание: $description", color = MaterialTheme.colorScheme.primary)

            Button(onClick = { navController.popBackStack() }) {
                Text("Назад")
            }
        }
    }

@Composable
@Preview(showBackground = true)
fun PastaDetailsPreview(){
    PastaDetails(navController = rememberNavController())
}