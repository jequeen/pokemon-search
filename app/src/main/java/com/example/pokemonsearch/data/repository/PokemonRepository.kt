package com.example.pokemonsearch.data.repository

import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.Pokemon

interface PokemonRepository {

    var currentlySelectedPokemon: Pokemon?

    var currentlySelectedMoveDetails: MoveDetails?


    /**
     * Fetches a Pokemon by its name from the API.
     *
     * @param name The name of the Pokemon to retrieve.
     * @return The fetched Pokemon object.
     */
    suspend fun getPokemonByName(name: String): Pokemon?

    /**
     * Fetches move details by its name from the API.
     *
     * @param name The name of the move to retrieve details for.
     * @return The fetched MoveDetails object.
     */
    suspend fun getMoveDetailsByName(name: String): MoveDetails?
}
