package com.jackle.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.jackle.pokemon.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pokeBall.setOnClickListener {
            val apiHelper = PokemonRepositoryImpl(PokemonFactoryApi.createAPI())
            viewModel.getPokemonList(apiHelper)
        }

        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                Log.d(
                    MainActivity::class.java.simpleName,
                    "NameEn ${it.name.english} NameTh ${it.hp}"
                )
            }
        }

        binding.bagPack.setOnClickListener {
            val intent = Intent(this, PokemonCollectionActivity::class.java)
            startActivity(intent)
        }

//        viewModel.getPokemonListCoroutine()
    }
}