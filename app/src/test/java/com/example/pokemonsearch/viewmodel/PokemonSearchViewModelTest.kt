package com.example.pokemonsearch.viewmodel

import com.example.pokemonsearch.MainDispatcherRule
import com.example.pokemonsearch.data.model.Move
import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.MoveHolder
import com.example.pokemonsearch.data.model.MoveType
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.data.model.Sprite
import com.example.pokemonsearch.data.repository.PokemonRepository
import com.example.pokemonsearch.repository.fake.FakePokemonRepository
import com.example.pokemonsearch.screens.PokemonSearchViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonSearchViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var repository: PokemonRepository

    @Before
    fun setup() {
        repository = FakePokemonRepository()

    }

    @Test
    fun `searchPokemonByName sets selectedPokemon when repository call succeeds`() = runTest {
        val pokemon = Pokemon("Pikachu", 100, 50, listOf(MoveHolder(Move("Pound"))), Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"))
        val viewModel = PokemonSearchViewModel(repository)

        viewModel.searchPokemonByName("Pikachu")

        assertEquals(pokemon, viewModel.selectedPokemon)
    }

    @Test
    fun `searchPokemonByName sets selectedPokemon to null when repository call fails`() = runTest {
        repository = FakePokemonRepository(false)
        val viewModel = PokemonSearchViewModel(repository)
        viewModel.searchPokemonByName("InvalidPokemonName")

        assertEquals(null, viewModel.selectedPokemon)
    }

    @Test
    fun `searchMoveByName sets selectedMove when repository call succeeds`() = runTest {
        val moveDetails = MoveDetails("Thunderbolt", 90, 15, 100, MoveType("Electric"))
        val viewModel = PokemonSearchViewModel(repository)

        viewModel.searchMoveByName("Thunderbolt")

        assertEquals(moveDetails, viewModel.selectedMove)
    }

    @Test
    fun `searchMoveByName sets selectedMove to null when repository call fails`() = runTest {
        repository = FakePokemonRepository(false)
        val viewModel = PokemonSearchViewModel(repository)

        viewModel.searchMoveByName("InvalidMoveName")

        assertEquals(null, viewModel.selectedMove)
    }

    @Test
    fun `clearSelectedPokemon sets selectedPokemon and selectedMove to null`() {
        val pokemon = Pokemon("Pikachu", 100, 50, listOf(MoveHolder(Move("Pound"))), Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"))
        val moveDetails = MoveDetails("Thunderbolt", 90, 15, 100, MoveType("Electric"))

        val viewModel = PokemonSearchViewModel(repository)

        viewModel.searchPokemonByName("Pikachu")
        viewModel.searchMoveByName("Thunderbolt")

        assertEquals(pokemon, viewModel.selectedPokemon)
        assertEquals(moveDetails, viewModel.selectedMove)

        viewModel.clearSelectedPokemon()

        assertEquals(null, viewModel.selectedPokemon)
        assertEquals(null, viewModel.selectedMove)
    }

}
