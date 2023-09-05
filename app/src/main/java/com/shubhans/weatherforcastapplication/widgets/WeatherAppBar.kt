package com.shubhans.weatherforcastapplication.widgets


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shubhans.weatherforcastapplication.model.Favorite
import com.shubhans.weatherforcastapplication.navigation.WeatherScreen
import com.shubhans.weatherforcastapplication.screens.favorite.favoriteViewModel
@SuppressLint("SuspiciousIndentation")
@Composable
fun WeatherAppBar(
    title: String = "Title",
    navController: NavController,
    favoriteViewModel: favoriteViewModel = hiltViewModel(),
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
)
 {
    val showDialog = remember {
        mutableStateOf(false)
    }
     val showIt = remember {
         mutableStateOf(false)
     }
     val context = LocalContext.current
    if(showDialog.value){
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }
    TopAppBar(title = {
                      Text(text = title,
                          color = MaterialTheme.colors.onSecondary,
                          style = TextStyle(fontWeight = FontWeight.Bold,
                              fontSize = 15.sp))
    },
       actions = {
          if (isMainScreen){
               IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "search icon")

                }
                IconButton(onClick = {
                    showDialog.value =true
                }) {
                    Icon(imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "More Icon" )

                }
            }else Box {}

       },
       navigationIcon = {
           if (icon != null) {
               Icon(imageVector = icon, contentDescription = null,
                   tint = MaterialTheme.colors.onSecondary,
                   modifier = Modifier.clickable {
                       onButtonClicked.invoke()
                   })
           }
           if (isMainScreen){
               val isAlreadyFavList =favoriteViewModel
                   .favList.collectAsState().value.filter { item ->
                       (item.city ==title.split(",")[0])
                   }
               if(isAlreadyFavList.isEmpty()){
                   Icon(imageVector = Icons.Default.Favorite,
                       contentDescription = "Favorite Icon",
                       modifier = Modifier
                           .scale(0.9f)
                           .clickable {
                               val dataList = title.split(",")
                               favoriteViewModel
                                   .insertFavorite(
                                       Favorite(
                                           city = dataList[0],
                                           country = dataList[1]
                                       )).run { showIt.value =true }
                           },
                       tint = Color.Red.copy(alpha = 0.6f))
               }else{ Box {
                   showIt.value =false
               } }
            ShowToast(context =context, showIt)

           }
       },
       backgroundColor =Color.Transparent,
     elevation = elevation)
}

fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
    if(showIt.value){
        Toast.makeText(context,"Added To Favorites",Toast.LENGTH_SHORT).show()

    }
}
@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("About", "Favorites", "Settings")
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 20.dp)) {
        DropdownMenu(expanded = expanded ,
            onDismissRequest = { expanded = false},
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)) {
            items.forEachIndexed { _, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (text) {
                            "About" -> Icons.Default.Info
                            "Favorites" -> Icons.Default.FavoriteBorder
                            else -> Icons.Default.Settings

                        }, contentDescription = null,
                        tint = Color.LightGray
                    )
                    Text(text = text,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                when (text) {
                                    "About" -> WeatherScreen.AboutScreen.name
                                    "Favorites" -> WeatherScreen.FavoriteScreen.name
                                    else -> WeatherScreen.SettingScreen.name
                                })


                        }, fontWeight = FontWeight.W300)
                }
            }
        }

    }

}




