package com.jackle.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jackle.pokemon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val pokemonList = listOf(
        "Bulbasaur" to "ฟุชิกิดาเนะ",
        "Ivysaur" to "ฟุชิกิโซ",
        "Venusaur" to "ฟุชิกิบานะ",
        "Charmander" to "ฮิโตคาเงะ",
        "Squirtle" to "เซนิกาเมะ",
        "Caterpie" to "คาเตอร์ปี",
        "Weedle" to "บีเดิล",
        "Pidgey" to "ป็อปโปะ",
        "Rattata" to "โครัตตา",
        "Pikachu" to "พิคาชู",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pokeBall.setOnClickListener {
            val pokemon = randomPokemon()
            Log.d(
                MainActivity::class.java.simpleName,
                "NameEn ${pokemon.first} NameTh ${pokemon.second}"
            )
        }

        binding.bagPack.setOnClickListener {
            val intent = Intent(this, PokemonCollectionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun randomPokemon() = pokemonList.random()
}