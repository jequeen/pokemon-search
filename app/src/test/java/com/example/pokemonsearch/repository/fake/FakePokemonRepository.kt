package com.example.pokemonsearch.repository.fake

import com.example.pokemonsearch.data.model.Move
import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.MoveHolder
import com.example.pokemonsearch.data.model.MoveType
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.data.model.Sprite
import com.example.pokemonsearch.data.repository.PokemonRepository
import com.example.pokemonsearch.data.repository.Result

class FakePokemonRepository(
    private val isPokemonSearchSuccessful: Boolean = true,
    private val isMoveDetailsFetchSuccessful: Boolean = true
) : PokemonRepository {

    override suspend fun getPokemonByName(name: String): Result<Pokemon> {
        return if (isPokemonSearchSuccessful) {
            val pokemon = Pokemon(
                "Pikachu",
                100,
                50,
                listOf(MoveHolder(Move("Pound"))),
                Sprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png")
            )
            Result.Success(pokemon)
        } else {
            Result.Error(Exception("Failed to fetch Pokemon"))
        }
    }

    override suspend fun getMoveDetailsByName(name: String): Result<MoveDetails> {
        return if (isMoveDetailsFetchSuccessful) {
            val moveDetails = MoveDetails("Pound", 100, 100, 100, MoveType("Lightning"))
            Result.Success(moveDetails)
        } else {
            Result.Error(Exception("Failed to fetch move details"))
        }
    }
}
