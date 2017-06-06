package ch.whattowat.api.http

import ch.whattowat.api.model.Film
import retrofit2.Call
import retrofit2.http.GET

internal interface Api {

    @GET("film/random/")
    fun getRandomFilm(): Call<Film>
}