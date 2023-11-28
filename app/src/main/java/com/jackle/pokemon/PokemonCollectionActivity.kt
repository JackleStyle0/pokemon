package com.jackle.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PokemonCollectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_collection)


        val intent = Intent(this, PokemonCollectionActivity::class.java)
        startActivity(intent)
    }
}