package com.example.pokemonsearch.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MoveDetails(
    @Json(name = "name")
    val name: String?,
    @Json(name = "accuracy")
    val accuracy: Int?,
    @Json(name = "pp")
    val pp: Int?,
    @Json(name = "power")
    val power: Int?,
    @Json(name = "type")
    val type: MoveType?
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class MoveType(
    @Json(name = "name")
    val name: String?
) : Parcelable
