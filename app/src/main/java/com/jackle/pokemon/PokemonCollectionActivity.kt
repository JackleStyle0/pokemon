package com.jackle.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jackle.pokemon.databinding.ActivityPokemonCollectionBinding
import com.jackle.pokemon.databinding.PokemonCardItemBinding

class PokemonCollectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonCollectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = PokemonAdapter(
            mockPokemonList()
        )

        binding.recyclerView.adapter = adapter

        // Part Transform activity to fragment
        if (savedInstanceState == null) {
            binding.contentContainer.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE

            supportFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, PokemonCollectionFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }
}