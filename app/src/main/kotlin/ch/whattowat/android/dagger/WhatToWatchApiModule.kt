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
    @ForWhatToWatch
    fun provideOkHttpClient(okhttpBuilder: OkHttpClient.Builder) = okhttpBuilder.build();

    @Provides
    @ForWhatToWatch
    fun provideGson(gsonBuilder: GsonBuilder) = gsonBuilder.create()

    @Provides
    @ForWhatToWatch
    fun provideRetrofit(application: WhatToWatchApplication,
                        @ForWhatToWatch httpClient: OkHttpClient,
                        @ForWhatToWatch gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(application.getString(R.string.what_to_watch_api_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
    }

    @Provides
    fun provideWhatToWatchApiEndpoint(@ForWhatToWatch retrofit: Retrofit): WhatToWatchEndpoint {
        return HttpWhatToWatchEndpoint(retrofit)
    }
}