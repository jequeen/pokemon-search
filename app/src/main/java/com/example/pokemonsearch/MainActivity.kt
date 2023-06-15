package com.example.pokemonsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemonsearch.screens.detail.MoveDetailRoute
import com.example.pokemonsearch.screens.list.PokemonInfoListRoute
import com.example.pokemonsearch.screens.search.PokemonSearchRoute
import com.example.pokemonsearch.ui.theme.PokemonSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonSearchTheme {
                PokemonSearchApp()
            }
        }
    }
}

@Composable
fun PokemonSearchApp() {
    PokemonSearchTheme {
        App()
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pokemonSearch") {
        composable("pokemonSearch") {
            PokemonSearchRoute(
                onPokemonSelected = {
                    navController.navigate("pokemonInfoList/${it.name}")
                }
            )
        }
        composable(
            route = "pokemonInfoList/{pokemonName}",
            arguments = listOf(navArgument("pokemonName"){
                type = NavType.StringType
            })
        ) {
            PokemonInfoListRoute(
                onBack = { navController.popBackStack() },
                onMoveAvailable = {
                    navController.navigate("moveDetail/$it")
                }
            )
        }
        composable(
            route = "moveDetail/{moveName}",
            arguments = listOf(navArgument("moveName"){
                type = NavType.StringType
            })
        ) {
            MoveDetailRoute(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
