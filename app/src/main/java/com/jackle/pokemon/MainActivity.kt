package com.jackle.pokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jackle.pokemon.detail.PokemonDetailActivity.Companion.POKEMON_DETAIL
import com.jackle.pokemon.collection.PokemonCollectionActivity
import com.jackle.pokemon.databinding.ActivityMainBinding
import com.jackle.pokemon.detail.PokemonDetailActivity
import com.jackle.pokemon.model.Pokemon
import com.jackle.pokemon.network.PokemonRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pokeBall.setOnClickListener {
            binding.pokeBallAnimate.visibility = View.VISIBLE
            val apiHelper = PokemonRepositoryImpl(PokemonFactoryApi.createAPI())
            viewModel.getPokemonList()
        }

        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                this@MainActivity.toast("GOTCHA!")
                binding.pokeBallAnimate.visibility = View.GONE
                navigateToDetail(it)
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

        binding.trainer.setOnClickListener {
            CustomDialog.newInstance().apply {
                onOkClickListener = { getTrainerName() }
            }.show(supportFragmentManager, CustomDialog::class.java.simpleName)
        }
    }

    private fun getTrainerName() {
//        val sharedPref =
//            getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE) ?: return
//        val trainerName = sharedPref.getString(KEY_TRAINER_NAME, "")
//
//        if (!trainerName.isNullOrEmpty()) {
//            this.toast("Trainer name is $trainerName")
//        }
    }

    private fun Context.toast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun navigateToDetail(detail: Pokemon) {
        val intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra(POKEMON_DETAIL,detail)
        startActivity(intent)
    }
}
