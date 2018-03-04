package ch.whattowat.android.dagger

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NetworkModule {

    @Provides
    fun provideOkhttpBuilder() = OkHttpClient.Builder()

    @Provides
    fun provideGsonBuilder() = GsonBuilder()
}