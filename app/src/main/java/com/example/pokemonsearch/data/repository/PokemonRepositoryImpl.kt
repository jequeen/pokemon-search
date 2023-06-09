package com.example.pokemonsearch.data.repository

import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.network.PokemonApiService
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val apiService: PokemonApiService): PokemonRepository {

    private var currentPokemon: Pokemon? = null

    private var currentMove: MoveDetails? = null

    override var currentlySelectedPokemon: Pokemon?
        get() = currentPokemon
        set(value) {
            currentPokemon = value
        }

    override var currentlySelectedMoveDetails: MoveDetails?
        get() = currentMove
        set(value) {
            currentMove = value
        }

    /**
     * Fetches a Pokemon by its name from the API.
     *
     * @param name The name of the Pokemon to retrieve.
     * @return The fetched Pokemon object.
     */
    override suspend fun getPokemonByName(name: String): Pokemon? {
        currentlySelectedPokemon = apiService.getPokemonByName(name)
        return currentlySelectedPokemon
    }

    /**
     * Fetches move details by its name from the API.
     *
     * @param name The name of the move to retrieve details for.
     * @return The fetched MoveDetails object.
     */
    override suspend fun getMoveDetailsByName(name: String): MoveDetails? {
        currentlySelectedMoveDetails = apiService.getMoveDetailsByName(name)
        return currentlySelectedMoveDetails
    }
}
