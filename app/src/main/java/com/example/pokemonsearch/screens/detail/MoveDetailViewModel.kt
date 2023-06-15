package com.example.pokemonsearch.screens.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.repository.PokemonRepository
import com.example.pokemonsearch.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveDetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val moveName: String = savedStateHandle["moveName"] ?: ""

    private val _selectedMove = mutableStateOf<MoveDetails?>(null)
    val selectedMove: State<MoveDetails?> = _selectedMove

    init {
        viewModelScope.launch {
            _selectedMove.value = when (val result = repository.getMoveDetailsByName(moveName)) {
                is Result.Success -> result.data
                is Result.Error -> null
            }
        }
    }
}
