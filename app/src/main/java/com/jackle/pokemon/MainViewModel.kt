package com.jackle.pokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch



class MainViewModel : ViewModel() {

    private val _uiState = MutableSharedFlow<Pokemon>()
    val uiState: SharedFlow<Pokemon> = _uiState.asSharedFlow()

//    private val pokemonList = listOf(
//        "Bulbasaur" to "ฟุชิกิดาเนะ",
//        "Ivysaur" to "ฟุชิกิโซ",
//        "Venusaur" to "ฟุชิกิบานะ",
//        "Charmander" to "ฮิโตคาเงะ",
//        "Squirtle" to "เซนิกาเมะ",
//        "Caterpie" to "คาเตอร์ปี",
//        "Weedle" to "บีเดิล",
//        "Pidgey" to "ป็อปโปะ",
//        "Rattata" to "โครัตตา",
//        "Pikachu" to "พิคาชู",
//    )

    fun randomPokemon(pokemonList: List<Pokemon>) = pokemonList.random()


    fun getPokemonList(apiHelper: PokemonRepositoryImpl) {
        viewModelScope.launch {
            apiHelper.getPokemonList()
                .flowOn(Dispatchers.IO)
                .catch { Log.d(MainViewModel::class.java.simpleName, ">> error") }
                .collect {
                   _uiState.emit(randomPokemon(it))
                }
        }
    }

    fun getPokemonListCoroutine() {
        viewModelScope.launch {
            val pokemonList = PokemonFactoryApi.createAPI().getPokemonList()
            if (pokemonList.isSuccessful) {
                Log.d(MainViewModel::class.java.simpleName, ">> error")
            } else {
                Log.d(MainViewModel::class.java.simpleName, ">> success")
            }
        }
    }
}