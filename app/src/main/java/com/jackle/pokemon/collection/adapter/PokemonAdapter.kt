package com.jackle.pokemon.collection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jackle.pokemon.model.Pokemon
import com.jackle.pokemon.R
import com.jackle.pokemon.databinding.PokemonCardItemBinding
import com.jackle.pokemon.extension.loadImage

class PokemonAdapter(
    private val pokemonList: List<Pokemon>,
    private val clickItem: (Pokemon) -> Unit
) : Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PokemonCardItemBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindView(
            pokemonList[position],
            clickItem
        )
    }
}

class PokemonViewHolder(
    private val bind: PokemonCardItemBinding,
) : ViewHolder(bind.root) {

    fun bindView(
        pokemon: Pokemon,
        clickItem: (Pokemon) -> Unit
    ) {
        pokemon.apply {
            bind.nameTv.text = pokemon.name.english
            bind.typeTv.text = pokemon.type.first()
            bind.imageView.loadImage(pokemon.imageUrl)
            bind.root.setOnClickListener {
                clickItem(pokemon)
            }
//            bind.imageView.background =
//                ContextCompat.getDrawable(
//                    bind.imageView.context,
//                    R.drawable.pokemon_placeholder
//                )
        }
    }
}