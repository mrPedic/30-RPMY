package com.example.a29_rpmy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    // Текущее выбранное блюдо (пицца)
    var selectedPizza by mutableStateOf<Pizza?>(null)
        private set

    // Сохраняем выбранную пиццу
    fun setPizza(pizza: Pizza) {
        selectedPizza = pizza
    }

    // Получаем выбранную пиццу (если нужно вне Composable)
    fun getPizza(): Pizza? = selectedPizza

    // Очищаем выбор (например, при выходе из деталей)
    fun clearSelection() {
        selectedPizza = null
    }
}
