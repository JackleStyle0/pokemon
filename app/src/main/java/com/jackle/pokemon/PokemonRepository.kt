package com.jackle.pokemon

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PokemonRepository {

    fun getPokemonList(): Flow<List<Pokemon>>
}

class PokemonRepositoryImpl(private val api: PokemonApi) : PokemonRepository {

    override fun getPokemonList(): Flow<List<Pokemon>> = flow {
        val res = api.getPokemonList()
        val body = res.body()
        if (res.isSuccessful && body != null) {
            emit(body)
        } else {
            throw NullPointerException()
        }
    }

}