package com.jackle.pokemon.extension

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken


inline fun <reified T> SharedPreferences.get(key: String, default: T): T {
    return try {
        Gson().fromJson(this.getString(key, null), object : TypeToken<T>() {}.type) ?: default
    } catch (e: JsonSyntaxException) {
        default
    }
}

fun <T> SharedPreferences.set(key: String, value: T) {
    this.edit().putString(key, Gson().toJson(value)).apply()
}