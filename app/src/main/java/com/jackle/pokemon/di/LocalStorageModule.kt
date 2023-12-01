package com.jackle.pokemon.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {

    private const val KEY_SHARED_PREFERENCES_NAME = "shared_preferences"

    @Provides
    fun provideSharePreference(
        @ApplicationContext context: Context
    ) = context.getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

}