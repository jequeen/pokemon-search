package com.example.pokemonsearch.screens.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.data.repository.PokemonRepository
import com.example.pokemonsearch.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoListViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonName: String = savedStateHandle["pokemonName"] ?: ""

    private val _selectedPokemon = mutableStateOf<Pokemon?>(null)
    val selectedPokemon: State<Pokemon?> = _selectedPokemon

    private val _moveSelected = mutableStateOf<String?>(null)
    val moveSelected: State<String?> = _moveSelected

    init {
        viewModelScope.launch {
            _selectedPokemon.value = when (val result = repository.getPokemonByName(pokemonName)) {
                is Result.Success -> result.data
                is Result.Error -> null
            }
        }
    }

    /**
     * Searches for move details by its name.
     *
     * @param name The name of the move to search for.
     */
    fun searchMoveByName(name: String) {
        viewModelScope.launch {
            _moveSelected.value = when (val result = repository.getMoveDetailsByName(name)){
                is Result.Success -> result.data.name
                is Result.Error -> null
            }
        }
    }

    /**
     * Clears the selected move
     */
    fun clearSelectedMove() {
        _moveSelected.value = null
    }
}
