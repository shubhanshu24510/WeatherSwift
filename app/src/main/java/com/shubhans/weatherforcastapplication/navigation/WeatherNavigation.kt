package com.shubhans.weatherforcastapplication.navigation

import AboutScreen
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.shubhans.weatherforcastapplication.screens.favorite.FavoritesScreen
import com.shubhans.weatherforcastapplication.screens.main.MainScreen
import com.shubhans.weatherforcastapplication.screens.main.MainViewModel
import com.shubhans.weatherforcastapplication.screens.search.SearchScreen
import com.shubhans.weatherforcastapplication.screens.setting.SettingScreen
import com.shubhans.weatherforcastapplication.screens.splash.WeatherSplashScreen

@SuppressLint("ComposableDestinationInComposeScope")
@ExperimentalComposeApi
@ExperimentalCoilApi
@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreen.SplashScreen.name) {
        composable(WeatherScreen.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }

            val route = WeatherScreen.MainScreen.name
            composable(
                  "$route/{city}",
                  arguments = listOf(
                        navArgument(name = "city") {
                              type = NavType.StringType
                        })
            ) { navBack ->
                navBack.arguments?.getString("city").let { city ->
                    val mainViewModel = hiltViewModel<MainViewModel>()
                    MainScreen(
                        navController = navController, mainViewModel,
                        city = city
                    )
                }
            }

        composable(WeatherScreen.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreen.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(WeatherScreen.FavoriteScreen.name) {
            FavoritesScreen(navController = navController)
        }
        composable(WeatherScreen.SettingScreen.name) {
           SettingScreen(navController = navController)
        }
    }
}







