package ch.whattowat.android.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ch.whattowat.android.data.whattowatch.WhatToWatchRepository
import ch.whattowat.api.model.Film
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val whatToWatchRepository: WhatToWatchRepository) : ViewModel() {

    private var _film: LiveData<Film>? = null
    val film: LiveData<Film>
        get() {
            if (_film == null) {
                retrieveFilm()
            }

            return _film ?: throw AssertionError("Set to null by another thread")
        }

    fun retrieveFilm() {
        _film = whatToWatchRepository.getRandomFilm()
    }
}