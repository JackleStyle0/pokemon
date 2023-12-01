package com.jackle.pokemon.collection

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jackle.pokemon.PREF_KEY_POKEMON
import com.jackle.pokemon.R
import com.jackle.pokemon.collection.adapter.PokemonAdapter
import com.jackle.pokemon.databinding.FragmentPokemonCollectionBinding
import com.jackle.pokemon.detail.PokemonDetailActivity
import com.jackle.pokemon.extension.get
import com.jackle.pokemon.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonCollectionFragment : Fragment() {

    private lateinit var binding: FragmentPokemonCollectionBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonCollectionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonList = sharedPreferences.get<List<Pokemon>>(PREF_KEY_POKEMON, listOf())
        val adapter = PokemonAdapter(
            pokemonList
        ) {
            findNavController()
                .navigate(
                    R.id.action_pokemonCollectionFragment_to_pokemonDetailFragment,
                    bundleOf(
                        PokemonDetailActivity.POKEMON_DETAIL to it
                    )
                )
        }

        binding.recyclerView.adapter = adapter
    }
}