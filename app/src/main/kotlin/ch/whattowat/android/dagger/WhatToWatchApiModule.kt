package ch.whattowat.android.dagger

import ch.whattowat.android.R
import ch.whattowat.android.WhatToWatchApplication
import ch.whattowat.api.WhatToWatchEndpoint
import ch.whattowat.api.http.HttpWhatToWatchEndpoint
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WhatToWatchApiModule {

    @Provides
    fun provideWhatToWatchApiEndpoint(retrofit: Retrofit): WhatToWatchEndpoint {
        return HttpWhatToWatchEndpoint(retrofit)
    }

    @Provides
    fun provideRetrofit(application: WhatToWatchApplication, httpClient: OkHttpClient,
                        gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(application.getString(R.string.what_to_watch_api_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
    }

    @Provides
    fun provideOkHttpClient() = OkHttpClient()

    @Provides
    fun provideGson() = GsonBuilder().create()
}