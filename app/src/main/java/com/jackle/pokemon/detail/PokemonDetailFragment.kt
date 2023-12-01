package com.jackle.pokemon.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jackle.pokemon.databinding.FragmentPokemonDetailBinding
import com.jackle.pokemon.extension.loadImage
import com.jackle.pokemon.model.Pokemon

class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonDetail = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(PokemonDetailActivity.POKEMON_DETAIL, Pokemon::class.java)
        } else {
            arguments?.getParcelable(PokemonDetailActivity.POKEMON_DETAIL)
        }
        pokemonDetail?.let {
            bindingView(it)
        }
    }

    private fun bindingView(detail: Pokemon) = with(binding) {
        titleEn.text = detail.name.english
        titleJp.text = detail.name.japanese
        pokemonImage.loadImage(detail.imageUrl)
        pokemonType.text = "Type: ${detail.type.joinToString(", ")}"
        pokemonHp.text = "HP: ${detail.hp}"
        pokemonAttack.text = "Attack: ${detail.attack}"
        pokemonDefense.text = "Defense: ${detail.defense}"
        pokemonSpeed.text = "Speed: ${detail.speed}"
    }

}
