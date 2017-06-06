package ch.whattowat.api

import ch.whattowat.api.model.Film
import ch.whattowat.api.model.WhatToWatchApiException

interface WhatToWatchApiResponse {

    fun onSuccess(film: Film)

    fun onError(error: WhatToWatchApiException)
}