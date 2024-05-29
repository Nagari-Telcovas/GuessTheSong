package com.telcovas.guessthesong.model

data class PackageList(  val status: String,
                         val message: String,
                         val response: List<Packinfo>)

data class Packinfo(
    val plan: String,
    val songs: String,
    val price:String
)
