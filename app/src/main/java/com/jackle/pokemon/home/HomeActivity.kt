package com.jackle.pokemon.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jackle.pokemon.R
import dagger.hilt.android.AndroidEntryPoint

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}