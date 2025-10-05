@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.example.a29_rpmy

import android.content.Intent
import androidx.compose.ui.text.font.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                NavGraph(navController, modifier = Modifier)
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    val context = LocalContext.current
    val pagerState = rememberPagerState(initialPage = 0) { 4 }
    var selectedTab by remember { mutableIntStateOf(pagerState.currentPage) }
    val namesPages = listOf<String>("Главная","Пицца","Паста","Заведения" )

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
                                text = namesPages[index],
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
                    0 -> ScreenOne()
                    1 -> PizzaMenu(navController,sharedViewModel)
                    2 -> PastaMenu(navController)
                    3 -> EstablishmentMenu(navController)
                    else -> {

                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScreenOne(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://static.tildacdn.com/tild6330-3538-4965-a164-383631626636/Pancake_Strawberry_B.jpg",
            contentDescription = "Пицца и закуски"
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(horizontal = 15.dp, vertical = 20.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Заголовок
                Text(
                    text = "Пицца и закуски",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                // Разделитель
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(2.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                )

                // Основной текст
                Text(
                    text = "С тех пор, как мы открыли свои двери в 2022 году, \"Пицца и закуски\" заработала репутацию одного из лучших ресторанов Пинского района.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Некоторые люди считают, что еда вне дома - это общение с друзьями и семьей. Мы считаем, что вкусной едой лучше всего наслаждаться, глядя в свой телефон.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    fontStyle = FontStyle.Italic
                )

                // Декоративный элемент
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(0.4f)
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                )
            }
        }
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
