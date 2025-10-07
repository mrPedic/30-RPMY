@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.a29_rpmy

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun PizzaMenu(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pizzaList) { pizza ->
                PizzaItem(
                    navController = navController,
                    pizza = pizza,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}


@Composable
fun PizzaItem(
    navController: NavController,
    pizza: Pizza,
    sharedViewModel: SharedViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .aspectRatio(0.8f)
            .clickable {
                sharedViewModel.setPizza(pizza)
                navController.navigate(SealedScreens.PizzaDetails.route)
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Фон — изображение пиццы
            Image(
                painter = rememberAsyncImagePainter(model = pizza.imageUrl),
                contentDescription = pizza.name,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            // Градиент
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                                MaterialTheme.colorScheme.surface
                            ),
                            startY = 0.6f,
                            endY = 380f
                        )
                    )
            )

            // Текст внизу карточки
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = pizza.name,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = pizza.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${pizza.price} руб.",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun PizzaDetails(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val pizza = sharedViewModel.selectedPizza
    val isDark = isSystemInDarkTheme()
    val cardBackground = if (isDark) Color(0xFF121212) else MaterialTheme.colorScheme.surface
    val backgroundColor = MaterialTheme.colorScheme.background

    if (pizza == null) {
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Контейнер для картинки с адаптивной маской
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(cardBackground)
        ) {
            Image(
                painter = rememberAsyncImagePainter(pizza.imageUrl),
                contentDescription = pizza.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Адаптивная тёмная маска для темной темы
            if (isDark) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xAA121212))
                )
            }
        }

        Text(
            text = pizza.name,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = pizza.description,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Состав:",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = pizza.composition,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }

        Text(
            text = "Цена: ${pizza.price} руб.",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                navController.popBackStack()
                sharedViewModel.clearSelection()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Назад в меню")
        }
    }
}


data class Pizza(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val composition: String,
    val price: Double
)

val pizzaList = listOf(
    Pizza(
        id = 1,
        name = "Мега грибная",
        description = "30 см, традиционное тесто, 600 г",
        imageUrl = "https://media.dodostatic.net/image/r:292x292/01997a84fcea705bb28a454aee30a44e.jpg",
        composition = "Моцарелла, соус альфредо, ветчина , шампиньоны свежие , лук красный , чеснок сухой , соус сливочный с грибами",
        price = 24.90
    ),
    Pizza(
        id = 2,
        name = "Двойная пепперони",
        description = "30 см, традиционное тесто, 610 г",
        imageUrl = "https://media.dodostatic.net/image/r:292x292/11ee7d5ec202addc89dbf11e483fa8c0.jpg",
        composition = "Моцарелла, пикантная пепперони, увеличенная порция моцареллы, томатный соус",
        price = 26.90
    ),
    Pizza(
        id = 3,
        name = "Додо",
        description = "30 см, традиционное тесто, 580 г",
        imageUrl = "https://media.dodostatic.net/image/r:292x292/0198608a4c4277b09176e1a70bde1398.jpg",
        composition = "Моцарелла, пикантная пепперони, томатный соус, брынза, томаты, итальянские травы",
        price = 22.90
    ),
    Pizza(
        id = 4,
        name = "Чоризо Фреш",
        description = "30 см, традиционное тесто, 570 г",
        imageUrl = "https://media.dodostatic.net/image/r:292x292/11ee7d5f0724840f8f7d7a05cfc4c2ec.jpg",
        composition = "Моцарелла, острая чоризо, томатный соус, халапеньо, сладкий перец, томаты, красный лук",
        price = 23.90
    ),
    Pizza(
        id = 5,
        name = "Карбонара",
        description = "30 см, традиционное тесто, 590 г",
        imageUrl = "https://media.dodostatic.net/image/r:292x292/0195adb93a2c7585b705a2b4b23c860d.jpg",
        composition = "Моцарелла, соус альфредо, бекон, ветчина, сыры чеддер и пармезан, томаты, красный лук, чеснок, итальянские травы",
        price = 25.90
    ),
    Pizza(
        id = 6,
        name = "Цыпленок барбекю",
        description = "30 см, традиционное тесто, 610 г",
        imageUrl = "https://media.dodostatic.net/image/r:292x292/11ee7d5ec9615e31ad4c9509ba59e663.jpg",
        composition = "Моцарелла, соус барбекю, цыпленок, томаты, красный лук, бекон, зелень",
        price = 26.90
    ),
    Pizza(
        id = 7,
        name = "Ветчина и сыр",
        description = "30 см, традиционное тесто, 540 г",
        imageUrl = "https://media.dodostatic.net/image/r:292x292/11ee7d5ee6205dbdaaf82f94a1b8766f.jpg",
        composition = "Моцарелла, соус альфредо, ветчина, смесь сыров чеддер и пармезан",
        price = 21.90
    ),
    Pizza(
        id = 8,
        name = "Кантри-пицца",
        description = "30 см, традиционное тесто, 680 г",
        imageUrl = "https://media.dodostatic.net/image/r:292x292/0195adb2c64b72caa1c188b4292233cd.jpg",
        composition = "Бекон, ветчина , жареный лук , маринованные огурчики , красный лук , чеснок , моцарелла, соус ранч, соус барбекю",
        price = 21.90
    ),


)


// Preview функции с заглушками
@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview(showBackground = true)
fun PizzaMenuPreview() {
    PizzaMenu(
        navController = rememberNavController(),
        sharedViewModel = SharedViewModel() // Создаем временный ViewModel для превью
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview(showBackground = true)
fun PizzaItemPreview() {
    PizzaItem(
        navController = rememberNavController(),
        pizza = pizzaList[0],
        sharedViewModel = SharedViewModel() // Создаем временный ViewModel для превью
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview(showBackground = true)
fun PizzaDetailsPreview() {
    val previewViewModel = SharedViewModel()
    previewViewModel.setPizza(pizzaList[0]) // Устанавливаем пиццу для превью

    PizzaDetails(
        navController = rememberNavController(),
        sharedViewModel = previewViewModel
    )
}
