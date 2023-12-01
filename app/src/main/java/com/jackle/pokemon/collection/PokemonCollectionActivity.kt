package com.jackle.pokemon.collection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jackle.pokemon.collection.adapter.PokemonAdapter
import com.jackle.pokemon.databinding.ActivityPokemonCollectionBinding

class PokemonCollectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonCollectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PokemonAdapter(listOf()) {

        }
        binding.recyclerView.adapter = adapter

        // Part Transform activity to fragment
//        if (savedInstanceState == null) {
//            binding.contentContainer.visibility = View.VISIBLE
//            binding.recyclerView.visibility = View.GONE
//
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.contentContainer, PokemonCollectionFragment.newInstance())
//                .addToBackStack(null)
//                .commit()
//        }
    }
}
