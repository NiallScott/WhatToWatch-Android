package ch.whattowat.api.model

import com.google.gson.annotations.SerializedName

data class Film(
        @SerializedName("title") val title: String = "",
        @SerializedName("year") val year: Int)