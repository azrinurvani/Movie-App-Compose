package com.azri.moviedbjetpackcompose

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: init...")
    }

    companion object{
        private const val TAG = "MovieApplication"
    }
}