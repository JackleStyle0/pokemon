package com.jackle.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jackle.pokemon.databinding.FragmentPokemonCollectionBinding

class PokemonCollectionFragment : Fragment() {

    private lateinit var binding: FragmentPokemonCollectionBinding

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

        val adapter = PokemonAdapter(
            mockPokemonList()
        )

        binding.recyclerView.adapter = adapter
    }

    companion object {

        fun newInstance(): PokemonCollectionFragment {
            return PokemonCollectionFragment()
        }
    }
}