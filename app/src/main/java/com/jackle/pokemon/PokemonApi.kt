package com.jackle.pokemon

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemons")
    suspend fun getPokemonList(): Response<List<Pokemon>>

}