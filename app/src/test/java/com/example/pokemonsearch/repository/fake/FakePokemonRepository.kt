package com.example.pokemonsearch.repository.fake

import com.example.pokemonsearch.data.model.Move
import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.MoveHolder
import com.example.pokemonsearch.data.model.MoveType
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.data.model.Sprite
import com.example.pokemonsearch.data.repository.PokemonRepository

class FakePokemonRepository(
    private val isPokemonSearchSuccessful: Boolean = true
) : PokemonRepository {

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

    override suspend fun getPokemonByName(name: String): Pokemon? {
        if(!isPokemonSearchSuccessful) {
            throw Exception()
        }
       currentPokemon = Pokemon(
            name = "Pikachu",
            weight = 50,
            height = 100,
            moves = listOf(MoveHolder(Move("Pound"))),
            sprites = Sprite(frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png")
        )
        return currentPokemon
    }

    override suspend fun getMoveDetailsByName(name: String): MoveDetails? {
        if(!isPokemonSearchSuccessful) {
            throw Exception()
        }
        currentMove = MoveDetails(
            name = "Thunderbolt",
            accuracy = 90,
            pp = 15,
            power = 100,
            type =  MoveType("Electric")
        )
        return currentMove
    }
}
