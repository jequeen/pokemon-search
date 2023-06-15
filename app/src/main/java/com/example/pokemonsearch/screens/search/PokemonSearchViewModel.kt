package com.example.pokemonsearch.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.data.repository.PokemonRepository
import com.example.pokemonsearch.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonSearchViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    private val _searchScreenState = mutableStateOf<PokemonSearchScreenState>(PokemonSearchScreenState.Ready)
    val searchScreenState: State<PokemonSearchScreenState> = _searchScreenState

    private val _selectedPokemon = mutableStateOf<Pokemon?>(null)
    val selectedPokemon: State<Pokemon?> = _selectedPokemon

    /**
     * Searches for a Pokemon by its name.
     *
     * @param name The name of the Pokemon to search for.
     */
    fun searchPokemonByName(name: String) {
        viewModelScope.launch {
            _searchScreenState.value = PokemonSearchScreenState.Loading
            _selectedPokemon.value = when (val result = repository.getPokemonByName(name.lowercase().trim())) {
                is Result.Success -> result.data
                is Result.Error -> {
                    _searchScreenState.value = PokemonSearchScreenState.Error
                    null
                }
            }
        }
    }

    /**
     * Clears the selected Pokemon and set search screen state to default.
     */
    fun clearSelectedPokemon() {
        _selectedPokemon.value = null
        _searchScreenState.value = PokemonSearchScreenState.Ready
    }
}
