package com.example.pokemonsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    navController.navigate("pokemonInfoList")
                }
            )
        }
        composable(
            "pokemonInfoList"
        ) {
            PokemonInfoListRoute(
                onBack = { navController.popBackStack() },
                onMoveSelected = {
                    navController.navigate("moveDetail")
                }
            )
        }
        composable(
            "moveDetail"
        ) {
            MoveDetailRoute(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
