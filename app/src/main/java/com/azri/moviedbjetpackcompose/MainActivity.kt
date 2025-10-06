package com.azri.moviedbjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.azri.moviedbjetpackcompose.ui.home.HomeScreen
import com.azri.moviedbjetpackcompose.ui.theme.MovieDBJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDBJetpackComposeTheme {
                HomeScreen {

                }
                //TODO Lanjut menit 1:11
                //https://www.youtube.com/watch?v=sjngqzKLl3M

            }
        }
    }
}