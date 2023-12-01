package com.jackle.pokemon

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jackle.pokemon.databinding.ActivityPokemonDetailBinding

class PokemonDetailActivity : AppCompatActivity() {

    companion object {
        const val POKEMON_DETAIL = "POKEMON_DETAIL"
    }

    private lateinit var binding: ActivityPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonDetail = intent.getParcelableExtra<Pokemon>(POKEMON_DETAIL)
        pokemonDetail?.let { bindingView(it) }
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

    private fun ImageView.loadImage(url: String) {
        Glide
            .with(this@PokemonDetailActivity)
            .load(url)
            .centerCrop()
            .into(this)
    }
}
