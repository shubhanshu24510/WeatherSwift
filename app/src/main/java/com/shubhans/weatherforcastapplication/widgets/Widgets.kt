package com.shubhans.weatherforcastapplication.widgets

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.shubhans.weatherforcastapplication.R
import com.shubhans.weatherforcastapplication.model.WeatherItem
import com.shubhans.weatherforcastapplication.utils.formatDate
import com.shubhans.weatherforcastapplication.utils.formatDateTime
import com.shubhans.weatherforcastapplication.utils.formatDecimals

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl ="http://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface(
        Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                formatDate(weather.dt)
                    .split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                WeatherStateImage(imageUrl = imageUrl)
            }
                Surface(
                    modifier = Modifier.padding(0.dp),
                    shape = CircleShape,
                    color = Color(0xFFFFC400)
                ) {
                    Text(
                        weather.weather[0].description,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.caption
                    )
                }
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue.copy(alpha = 0.7f),
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(formatDecimals(weather.temp.max) + "º")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.LightGray
                        )
                    ) {
                        append(formatDecimals(weather.temp.min) + "º")
                    }
                })
            }
        }
    }
@Composable
fun SunsetSunRiseRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise",
                modifier = Modifier.size(30.dp)
            )
            Text(text = formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.caption)
        }
        Row() {
            Text(
                text = formatDateTime(weather.sunset),
                style = MaterialTheme.typography.caption
            )
            Image(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {Row() {
        Icon(
            painter = painterResource(id = R.drawable.humidity1),
            contentDescription = "humidity icon",
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = "${weather.humidity}%",
            style = MaterialTheme.typography.caption
        )
    }
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.pressure1),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.pressure} psi",
                style = MaterialTheme.typography.caption
            )

        }
        Row() {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.humidity} mph",
                style = MaterialTheme.typography.caption
            )

        }

    }
}
@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl), contentDescription =
        "Icon Image",
        modifier = Modifier.size(80.dp)
    )
}

