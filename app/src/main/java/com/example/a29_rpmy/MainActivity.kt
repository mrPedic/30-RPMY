@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.example.a29_rpmy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.a29_rpmy.ui.theme._29RPMYTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _29RPMYTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val pagerState = rememberPagerState(initialPage = 0) { 3 }
    var selectedTab by remember { mutableIntStateOf(pagerState.currentPage) }

    LaunchedEffect(selectedTab) {
        pagerState.scrollToPage(selectedTab)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }

    Scaffold(
        topBar = {
            Column() {
                TopAppBar(
                    modifier = Modifier,
                    title = {Text(text = "Пицца и закуски")},
                    actions = {
                        IconButton(
                            onClick = {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(
                                        Intent.EXTRA_TEXT,
                                        "Приложение сделал Влад Ващило"
                                    )
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, "Поделиться через")
                                context.startActivity(shareIntent)
                            }) {
                            Icon(Icons.Default.Share, contentDescription = "Поделиться")
                        }

                        IconButton(
                            onClick = {}) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Сделать заказ")

                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
                TabRow(
                    selectedTabIndex = selectedTab
                ) {
                    for (index in 0 until pagerState.pageCount) {
                        Tab(
                            selected = index == selectedTab,
                            onClick = {
                                selectedTab = index
                            }) {
                            Text(
                                text = "Tab $index",
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    ) {
            paddingValues ->

        Column (
            modifier = Modifier.padding(paddingValues)
        ){
            HorizontalPager(state = pagerState) {
                    currentPage ->
                when(currentPage){
                    0 -> ScreenOne(currentPage.toString())
                    1 -> ScreenTwo()
                    2 -> ScreenThree()
                    else -> {

                    }
                }
            }
        }
    }
}

@Composable
fun ScreenOne(pageNumber: String){
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://static.tildacdn.com/tild6330-3538-4965-a164-383631626636/Pancake_Strawberry_B.jpg",
            contentDescription = "null"
        )
    }
}

@Composable
fun ScreenTwo(){
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "I'm now on screen without an index")
    }
}

@Composable
fun ScreenThree(){
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "I am on screen three. Сосал?")
    }
}