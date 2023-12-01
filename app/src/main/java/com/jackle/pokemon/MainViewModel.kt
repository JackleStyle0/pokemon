package com.jackle.pokemon

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackle.pokemon.extension.get
import com.jackle.pokemon.extension.set
import com.jackle.pokemon.model.Pokemon
import com.jackle.pokemon.network.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _uiState = MutableSharedFlow<Pokemon>()
    val uiState: SharedFlow<Pokemon> = _uiState.asSharedFlow()

    private fun randomPokemon(pokemonList: List<Pokemon>) = pokemonList.random()

    fun getPokemonList() {
        viewModelScope.launch {
            repository.getPokemonList()
                .flowOn(Dispatchers.IO)
                .catch { Log.d(MainViewModel::class.java.simpleName, ">> error") }
                .collect {
                    val pokemon = randomPokemon(it)
                    savePokemonToPreference(pokemon)
                    _uiState.emit(pokemon)
                }
        }
    }

    private fun savePokemonToPreference(pokemon: Pokemon) {
        sharedPreferences
            .get<List<Pokemon>>(PREF_KEY_POKEMON, listOf())
            .toMutableList()
            .apply {
                add(pokemon)
            }.also {
                sharedPreferences.set(PREF_KEY_POKEMON, it)
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
