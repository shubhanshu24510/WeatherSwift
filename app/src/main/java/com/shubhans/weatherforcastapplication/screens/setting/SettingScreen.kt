package com.shubhans.weatherforcastapplication.screens.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shubhans.weatherforcastapplication.widgets.WeatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(navController: NavHostController,settingViewModel: SettingViewModel= hiltViewModel()) {
    var unitToggleState by remember{ mutableStateOf(false)}
        val measurementUnits = listOf("Imperial (F)","Metric(C)")
    var choiseState by remember { mutableStateOf("") }

   Scaffold(topBar = {
       WeatherAppBar(
           title = "Setting",
           icon = Icons.Default.ArrowBack,
           isMainScreen = false, navController = navController)
   }) {
       Surface(modifier = Modifier
           .fillMaxWidth()
           .fillMaxHeight()) {
           Column(verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally) {
               Text(text = "Change Units of Measurement",
                   modifier = Modifier.padding(bottom = 15.dp))
               IconToggleButton(checked = !unitToggleState, onCheckedChange = {
                       unitToggleState = !it
                       if (unitToggleState) {
                           choiseState = "Imperial (F)"
                       } else {
                           choiseState = "Metric(C)"
                       }
                   }, modifier = Modifier
                   .clip(shape = CircleShape)
                   .background(Color.Blue.copy(alpha = 0.6f))
                   .padding(2.dp)
               ) {
                   Text(text = if (unitToggleState) "Fahrenhietm °F" else "Celsius °C")
               }
           }
           Button(onClick = {},
               modifier = Modifier.padding(3.dp).align(Cen)) {

           }
       }
   }
}