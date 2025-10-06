package com.azri.moviedbjetpackcompose.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.azri.moviedbjetpackcompose.movie.domain.models.Movie
import com.azri.moviedbjetpackcompose.ui.home.itemSpacing

@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    discoverMovies : List<Movie>,
    trendingMovies : List<Movie>,
    onMovieClick : (Int) -> Unit
){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        item {
            Card (modifier = Modifier.fillMaxWidth()){
                //Navigate See More Discover Movies
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(itemSpacing),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Discover Movies",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = {
                            //TODO : Navigate to discover screen
                        }
                    ){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "More Discover Movies"
                        )
                    }
                }

                //Movie Cover Image
                LazyRow {
                    items(discoverMovies){
                        MovieCoverImage(
                            movie = it,
                            onMovieClick = onMovieClick
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(itemSpacing),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Trending Now",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Trending Now"
                        )
                    }
                }
                LazyRow {
                    items(trendingMovies){
                        MovieCoverImage(
                            movie = it,
                            onMovieClick = onMovieClick
                        )
                    }
                }
            }
        }
    }
}