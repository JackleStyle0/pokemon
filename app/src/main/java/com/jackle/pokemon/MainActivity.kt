package com.jackle.pokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jackle.pokemon.CustomDialog.Companion.KEY_SHARED_PREFERENCES_NAME
import com.jackle.pokemon.CustomDialog.Companion.KEY_TRAINER_NAME
import com.jackle.pokemon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val pokemonList = listOf(
        "Bulbasaur" to "ฟุชิกิดาเนะ",
        "Ivysaur" to "ฟุชิกิโซ",
        "Venusaur" to "ฟุชิกิบานะ",
        "Charmander" to "ฮิโตคาเงะ",
        "Squirtle" to "เซนิกาเมะ",
        "Caterpie" to "คาเตอร์ปี",
        "Weedle" to "บีเดิล",
        "Pidgey" to "ป็อปโปะ",
        "Rattata" to "โครัตตา",
        "Pikachu" to "พิคาชู",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pokeBall.setOnClickListener {
            val pokemon = randomPokemon()
            Log.d(
                MainActivity::class.java.simpleName,
                "NameEn ${pokemon.first} NameTh ${pokemon.second}"
            )
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

    private fun randomPokemon() = pokemonList.random()

    private fun getTrainerName() {
        val sharedPref = getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE) ?: return
        val trainerName = sharedPref.getString(KEY_TRAINER_NAME, "")

        if (!trainerName.isNullOrEmpty()) {
            this.toast(trainerName)
        }
    }

    private fun Context.toast(message: String) =
        Toast.makeText(this, "Trainer name is $message", Toast.LENGTH_SHORT).show()
}