package com.jackle.pokemon.extension

import android.content.SharedPreferences
import com.google.gson.Gson


inline fun <reified T> SharedPreferences.get(key: String, default: String): T {
    return Gson().fromJson(this.getString(key, default), T::class.java)
}

fun <T> SharedPreferences.set(key: String, value: T) {
    this.set(key, Gson().toJson(value))
}