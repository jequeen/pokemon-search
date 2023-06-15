package com.example.pokemonsearch.data.repository

import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.Pokemon

interface PokemonRepository {

    /**
     * Fetches a Pokemon by its name from the API.
     *
     * @param name The name of the Pokemon to retrieve.
     * @return The fetched Pokemon object wrapped in a Result.Success, or Result.Error if there was an error.
     */
    suspend fun getPokemonByName(name: String): Result<Pokemon>

    /**
     * Fetches move details by its name from the API.
     *
     * @param name The name of the move to retrieve details for.
     * @return The fetched MoveDetails object wrapped in a Result.Success, or Result.Error if there was an error.
     */
    suspend fun getMoveDetailsByName(name: String): Result<MoveDetails>
}
