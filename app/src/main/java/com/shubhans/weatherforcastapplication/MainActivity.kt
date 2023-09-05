package com.shubhans.weatherforcastapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.shubhans.weatherforcastapplication.navigation.WeatherNavigation
import com.shubhans.weatherforcastapplication.ui.theme.WeatherForcastApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()

        }
    }
}
@OptIn(ExperimentalComposeApi::class, ExperimentalCoilApi::class)
@Composable
fun WeatherApp() {
    WeatherForcastApplicationTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                color = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    WeatherNavigation()
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherForcastApplicationTheme {
    }
}