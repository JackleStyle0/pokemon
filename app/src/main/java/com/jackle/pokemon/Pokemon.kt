package com.jackle.pokemon

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: Name,
    @SerializedName("type")
    val type: List<String>,
    @SerializedName("HP")
    val hp: Int,
    @SerializedName("Attack")
    val attack: Int,
    @SerializedName("Defense")
    val defense: Int,
    @SerializedName("Speed")
    val speed: Int,
    @SerializedName("imageUrl")
    val imageUrl: String
) : Parcelable

@Parcelize
data class Name(
    val english: String,
    val japanese: String
) : Parcelable
