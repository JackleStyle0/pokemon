package com.jackle.pokemon

data class Pokemon(
    val nameEn: String,
    val nameTh: String,
    val type: String,
    val imageUrl: String
)


fun mockPokemonList() {
    Pokemon("Bulbasaur",	"ฟุชิกิดาเนะ", "Grass", "")
    Pokemon("Ivysaur",	"ฟุชิกิโซ", "Grass", "")
    Pokemon("Venusaur",	"ฟุชิกิบานะ", "Grass", "")
    Pokemon("Charmander",	"ฮิโตคาเงะ", "Fire", "")
    Pokemon("Squirtle",	"เซนิกาเมะ", "Water", "")
    Pokemon("Caterpie",	"คาเตอร์ปี", "Bug", "")
    Pokemon("Weedle",	"บีเดิล", "Bug", "")
    Pokemon("Pidgey",	"ป็อปโปะ", "Flying", "")
    Pokemon("Rattata",	"โครัตตา", "Normal", "")
    Pokemon("Pikachu",	"พิคาชู", "Eletric", "")
}