package com.jackle.pokemon

import com.google.gson.annotations.SerializedName

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
)

data class Name(
    val english: String,
    val japanese: String
)


//fun mockPokemonList() = listOf(
//    Pokemon("Bulbasaur", "ฟุชิกิดาเนะ", "Grass", ""),
//    Pokemon("Ivysaur", "ฟุชิกิโซ", "Grass", ""),
//    Pokemon("Venusaur", "ฟุชิกิบานะ", "Grass", ""),
//    Pokemon("Charmander", "ฮิโตคาเงะ", "Fire", ""),
//    Pokemon("Squirtle", "เซนิกาเมะ", "Water", ""),
//    Pokemon("Caterpie", "คาเตอร์ปี", "Bug", ""),
//    Pokemon("Weedle", "บีเดิล", "Bug", ""),
//    Pokemon("Pidgey", "ป็อปโปะ", "Flying", ""),
//    Pokemon("Rattata", "โครัตตา", "Normal", ""),
//    Pokemon("Pikachu", "พิคาชู", "Eletric", ""),
//)