package com.pop_flake.di

import android.content.Context
import com.pop_flake.other.Validation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
class ValidationModule {

    @Provides
    fun providesValidation(@ActivityContext context: Context): Validation {
        return Validation(context)
    }

}