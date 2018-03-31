package ch.whattowat.android.data.whattowatch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ch.whattowat.android.util.Result
import ch.whattowat.api.WhatToWatchApiResponse
import ch.whattowat.api.WhatToWatchEndpoint
import ch.whattowat.api.model.Film
import ch.whattowat.api.model.WhatToWatchApiException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WhatToWatchRepository @Inject constructor(
        private var whatToWatchEndpoint: WhatToWatchEndpoint) {

    fun getRandomFilm(): LiveData<Result<Film, WhatToWatchApiException>> {
        val data = MutableLiveData<Result<Film, WhatToWatchApiException>>()

        whatToWatchEndpoint.getRandomFilm(object : WhatToWatchApiResponse {
            override fun onSuccess(film: Film) {
                data.value = Result(film)
            }

            override fun onError(error: WhatToWatchApiException) {
                data.value = Result(error)
            }
        })

        return data
    }
}