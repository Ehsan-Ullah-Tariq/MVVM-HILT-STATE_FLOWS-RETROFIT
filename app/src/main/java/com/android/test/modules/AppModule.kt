package com.android.test.modules

import android.content.Context
import android.os.StrictMode
import com.android.test.utils.AppLifecycleObserver
import com.android.test.utils.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAppLifecycleObserver(@ApplicationContext context: Context): AppLifecycleObserver {
        val oldPolicy = StrictMode.allowThreadDiskReads()
        try {
            return AppLifecycleObserver(context)
        } finally {
            StrictMode.setThreadPolicy(oldPolicy)
        }
        //return AppPreferences(context)
    }

    @Provides
    @Singleton
    fun providesAppPreferences(@ApplicationContext context: Context): AppPreferences {
        val oldPolicy = StrictMode.allowThreadDiskReads()
        try {
            return AppPreferences(context)
        } finally {
            StrictMode.setThreadPolicy(oldPolicy)
        }
        //return AppPreferences(context)
    }

}