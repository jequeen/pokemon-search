package com.example.pokemonsearch.screens.search

sealed class PokemonSearchScreenState {
    object Loading : PokemonSearchScreenState()
    object Error : PokemonSearchScreenState()
    object Ready : PokemonSearchScreenState()
}
