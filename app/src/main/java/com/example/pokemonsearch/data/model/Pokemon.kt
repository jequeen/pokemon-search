package com.example.pokemonsearch.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Pokemon(
    @Json(name = "name")
    val name: String?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "weight")
    val weight: Int?,
    @Json(name = "moves")
    val moves: List<MoveHolder>?,
    @Json(name = "sprites")
    val sprites: Sprite?
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class MoveHolder(
    @Json(name = "move")
    val move: Move
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Move(
    @Json(name = "name")
    val name: String
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Sprite(
    @Json(name = "front_default")
    val frontDefault: String?
) : Parcelable
