package com.example.pokemonsearch.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.data.repository.PokemonRepository
import com.example.pokemonsearch.screens.search.PokemonSearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonSearchViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    // Pokemon Search Screen state variable
    var searchScreenState by mutableStateOf<PokemonSearchScreenState>(PokemonSearchScreenState.Ready)

    // State for the selected Pokemon
    var selectedPokemon by mutableStateOf<Pokemon?>(null)
        private set

    // State for the selected move details
    var selectedMove by mutableStateOf<MoveDetails?>(null)
        private set

    /**
     * Searches for a Pokemon by its name.
     *
     * @param name The name of the Pokemon to search for.
     */
    fun searchPokemonByName(name: String) {
        viewModelScope.launch {
            searchScreenState = PokemonSearchScreenState.Loading
            val result = try {
                repository.getPokemonByName(name.lowercase())
            } catch (e: Exception) {
                searchScreenState = PokemonSearchScreenState.Error
                null
            }
            selectedPokemon = result
        }
    }

    /**
     * Searches for move details by its name.
     *
     * @param name The name of the move to search for.
     */
    fun searchMoveByName(name: String) {
        viewModelScope.launch {
            val result = try {
                repository.getMoveDetailsByName(name)
            } catch (e: Exception) {
                null
            }
            selectedMove = result
        }
    }

    /**
     * Clears the selected Pokemon, move details, and set search screen state to default.
     */
    fun clearSelectedPokemon() {
        selectedPokemon = null
        selectedMove = null
        searchScreenState = PokemonSearchScreenState.Ready
    }
}
