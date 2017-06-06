package ch.whattowat.api

interface WhatToWatchEndpoint {

    fun getRandomFilm(callback: WhatToWatchApiResponse)
}