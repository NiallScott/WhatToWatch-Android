package ch.whattowat.android.dagger

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(
        NetworkModule::class,
        WhatToWatchApiModule::class
))
interface DataComponent {

}