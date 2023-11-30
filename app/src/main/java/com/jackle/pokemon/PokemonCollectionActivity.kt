package com.jackle.pokemon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jackle.pokemon.databinding.ActivityPokemonCollectionBinding

class PokemonCollectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonCollectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = PokemonAdapter(listOf())
        binding.recyclerView.adapter = adapter
    }
}