package ch.whattowat.android.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ch.whattowat.api.WhatToWatchApiResponse
import ch.whattowat.api.WhatToWatchEndpoint
import ch.whattowat.api.model.Film
import ch.whattowat.api.model.WhatToWatchApiException
import javax.inject.Inject

class MainViewModel @Inject constructor(private val endpoint: WhatToWatchEndpoint): ViewModel() {

    val liveFilm: LiveData<Film>
        get() = mutableLiveFilm
    private val mutableLiveFilm = MutableLiveData<Film>()

    fun init() {
        val currentFilm = liveFilm.value

        if (currentFilm == null) {
            loadRandomFilm()
        }
    }

    private fun loadRandomFilm() {
        endpoint.getRandomFilm(object : WhatToWatchApiResponse {
            override fun onSuccess(film: Film) {
                mutableLiveFilm.value = film
            }

            override fun onError(error: WhatToWatchApiException) {

            }
        })
    }
}