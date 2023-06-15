package com.example.pokemonsearch.viewmodel

import com.example.pokemonsearch.MainDispatcherRule
import com.example.pokemonsearch.data.model.Move
import com.example.pokemonsearch.data.model.MoveHolder
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.data.model.Sprite
import com.example.pokemonsearch.data.repository.PokemonRepository
import com.example.pokemonsearch.repository.fake.FakePokemonRepository
import com.example.pokemonsearch.screens.search.PokemonSearchScreenState
import com.example.pokemonsearch.screens.search.PokemonSearchViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonSearchViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var repository: PokemonRepository
    private lateinit var viewModel: PokemonSearchViewModel

    @Before
    fun setup() {
        repository = FakePokemonRepository()
        viewModel = PokemonSearchViewModel(repository)
    }

    @Test
    fun `searchPokemonByName sets selectedPokemon when repository call succeeds`() = runTest {
        val pokemon = Pokemon("Pikachu", 100, 50, listOf(MoveHolder(Move("Pound"))), Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"))

        viewModel.searchPokemonByName("Pikachu")

        assertEquals(pokemon, viewModel.selectedPokemon.value)
    }

    @Test
    fun `searchPokemonByName sets selectedPokemon to null when repository call fails`() = runTest {
        repository = FakePokemonRepository(isPokemonSearchSuccessful = false)
        viewModel = PokemonSearchViewModel(repository)

        viewModel.searchPokemonByName("InvalidPokemonName")

        assertNull(viewModel.selectedPokemon.value)
    }

    @Test
    fun `searchPokemonByName sets searchScreenState to Loading`() = runTest {
        viewModel.searchPokemonByName("Pikachu")

        assertEquals(PokemonSearchScreenState.Loading, viewModel.searchScreenState.value)
    }

    @Test
    fun `searchPokemonByName sets searchScreenState to Error when repository call fails`() = runTest {
        repository = FakePokemonRepository(isPokemonSearchSuccessful = false)
        viewModel = PokemonSearchViewModel(repository)

        viewModel.searchPokemonByName("InvalidPokemonName")

        assertEquals(PokemonSearchScreenState.Error, viewModel.searchScreenState.value)
    }

    @Test
    fun `clearSelectedPokemon sets selectedPokemon to null`() {
        viewModel.clearSelectedPokemon()

        assertNull(viewModel.selectedPokemon.value)
    }

    @Test
    fun `clearSelectedPokemon sets searchScreenState to Ready`() {
        viewModel.clearSelectedPokemon()

        assertEquals(PokemonSearchScreenState.Ready, viewModel.searchScreenState.value)
    }
}
