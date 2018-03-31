package ch.whattowat.android.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import ch.whattowat.android.data.whattowatch.WhatToWatchRepository
import ch.whattowat.android.util.Result
import ch.whattowat.api.model.Film
import ch.whattowat.api.model.WhatToWatchApiException
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val whatToWatchRepository: WhatToWatchRepository) : ViewModel() {

    val loadSuccess: LiveData<Film>
        get() = _loadSuccess
    val loadFailure: LiveData<WhatToWatchApiException>
        get() = _loadFailure

    private val _loadSuccess = MediatorLiveData<Film>()
    private val _loadFailure = MediatorLiveData<WhatToWatchApiException>()

    private var currentFilmResult: LiveData<Result<Film, WhatToWatchApiException>>? = null

    fun loadFilm() {
        currentFilmResult?.let {
            _loadSuccess.removeSource(it)
            _loadFailure.removeSource(it)
        }

        currentFilmResult = whatToWatchRepository.getRandomFilm().also {
            _loadSuccess.addSource(it, this::handleLoadCompleted)
            _loadFailure.addSource(it, this::handleLoadCompleted)
        }
    }

    private fun handleLoadCompleted(result: Result<Film, WhatToWatchApiException>?) {
        if (result != null) {
            if (result.isError()) {
                _loadFailure.value = result.error
                _loadSuccess.value = null
            } else {
                _loadSuccess.value = result.success
                _loadFailure.value = null
            }
        } else {
            _loadSuccess.value = null
            _loadFailure.value = null
        }
    }
}