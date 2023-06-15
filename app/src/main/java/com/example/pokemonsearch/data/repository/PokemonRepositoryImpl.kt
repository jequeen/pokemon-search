package com.example.pokemonsearch.data.repository

import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.network.PokemonApiService
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val apiService: PokemonApiService) :
    PokemonRepository {

    private var currentPokemon: Pokemon? = null
    private var currentMove: MoveDetails? = null

    /**
     * Fetches a Pokemon by its name from the API.
     *
     * @param name The name of the Pokemon to retrieve.
     * @return The fetched Pokemon object wrapped in a Result.Success, or Result.Error if there was an error.
     */
    override suspend fun getPokemonByName(name: String): Result<Pokemon> {
        currentPokemon?.let {
            if(it.name == name) {
                return Result.Success(it)
            }
        }
        return try {
            val pokemon = apiService.getPokemonByName(name)
            currentPokemon = pokemon
            Result.Success(pokemon)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Fetches move details by its name from the API.
     *
     * @param name The name of the move to retrieve details for.
     * @return The fetched MoveDetails object wrapped in a Result.Success, or Result.Error if there was an error.
     */
    override suspend fun getMoveDetailsByName(name: String): Result<MoveDetails> {
        currentMove?.let {
            if(it.name == name) {
                return Result.Success(it)
            }
        }
        return try {
            val moveDetails = apiService.getMoveDetailsByName(name)
            currentMove = moveDetails
            Result.Success(moveDetails)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
