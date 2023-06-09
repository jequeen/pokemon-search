package com.example.pokemonsearch.network

import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): Pokemon

    @GET("move/{name}")
    suspend fun getMoveDetailsByName(@Path("name") name: String): MoveDetails

}
