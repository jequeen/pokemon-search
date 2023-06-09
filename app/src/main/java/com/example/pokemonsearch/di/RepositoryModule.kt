package com.example.pokemonsearch.di

import com.example.pokemonsearch.data.repository.PokemonRepository
import com.example.pokemonsearch.data.repository.PokemonRepositoryImpl
import com.example.pokemonsearch.network.PokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(apiService: PokemonApiService): PokemonRepository {
        return PokemonRepositoryImpl(apiService)
    }
}
