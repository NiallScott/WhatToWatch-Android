package ch.whattowat.android.dagger

import dagger.Module

@Module(includes = [
    NetworkModule::class,
    WhatToWatchApiModule::class,
    ViewModelModule::class,
    ActivityModule::class
])
class ApplicationModule