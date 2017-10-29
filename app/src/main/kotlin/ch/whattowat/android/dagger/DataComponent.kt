package ch.whattowat.android.dagger

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(WhatToWatchApiModule::class))
interface DataComponent {

}