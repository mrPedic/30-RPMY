package com.example.a29_rpmy

sealed class SealedScreens(val route: String) {
    object Main: SealedScreens(route = "main")
    object Order: SealedScreens(route = "order")
    object PizzaMenu: SealedScreens(route = "pizza-menu")
    object PastaMenu: SealedScreens(route = "pasta-menu")
    object EstablishmentMenu: SealedScreens(route = "establishment-menu")

    object PizzaDetails: SealedScreens(route = "pizza-details/{name}/{description}"){
        fun createRoute(name:String, description: String) = "pizza-details/$name/$description"
    }
    object PastaDetails: SealedScreens(route = "pasta-details/{name}/{description}"){
        fun createRoute(name:String, description: String) = "pasta-details/$name/$description"
    }
    object EstablishmentDetails: SealedScreens(route = "establishment-details/{name}/{space}/{timeWork}"){
        fun createRoute(name:String, space: String, timeWork: String) = "establishment-details/$name/$space/$timeWork"
    }

}