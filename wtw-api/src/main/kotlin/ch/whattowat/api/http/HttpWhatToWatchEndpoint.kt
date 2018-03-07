package ch.whattowat.api.http

import ch.whattowat.api.WhatToWatchApiResponse
import ch.whattowat.api.WhatToWatchEndpoint
import ch.whattowat.api.model.Film
import ch.whattowat.api.model.WhatToWatchApiException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class HttpWhatToWatchEndpoint(retrofit: Retrofit) : WhatToWatchEndpoint {

    private val service = retrofit.create(Api::class.java)

    override fun getRandomFilm(callback: WhatToWatchApiResponse) {
        service.getRandomFilm().enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        callback.onSuccess(body)
                    } else {
                        callback.onError(WhatToWatchApiException("The response body is empty"))
                    }
                } else {
                    callback.onError(
                            WhatToWatchApiException("HTTP status code: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {
                callback.onError(WhatToWatchApiException(t))
            }
        })
    }
}