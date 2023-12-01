package com.jackle.pokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jackle.pokemon.CustomDialog.Companion.KEY_SHARED_PREFERENCES_NAME
import com.jackle.pokemon.CustomDialog.Companion.KEY_TRAINER_NAME
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

        binding.trainer.setOnClickListener {
            CustomDialog.newInstance().apply {
                onOkClickListener = { getTrainerName() }
            }.show(supportFragmentManager, CustomDialog::class.java.simpleName)
        }
    }

    private fun getTrainerName() {
        val sharedPref =
            getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE) ?: return
        val trainerName = sharedPref.getString(KEY_TRAINER_NAME, "")

        if (!trainerName.isNullOrEmpty()) {
            this.toast(trainerName)
        }
    }

    private fun Context.toast(message: String) =
        Toast.makeText(this, "Trainer name is $message", Toast.LENGTH_SHORT).show()
}
