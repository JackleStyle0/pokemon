package com.jackle.pokemon.di

import com.jackle.pokemon.network.PokemonApi
import com.jackle.pokemon.network.PokemonRepository
import com.jackle.pokemon.network.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providePokemonRepository(
        api: PokemonApi
    ): PokemonRepository = PokemonRepositoryImpl(api)
}