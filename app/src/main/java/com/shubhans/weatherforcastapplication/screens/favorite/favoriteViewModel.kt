package com.shubhans.weatherforcastapplication.screens.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubhans.weatherforcastapplication.model.Favorite
import com.shubhans.weatherforcastapplication.model.Unit
import com.shubhans.weatherforcastapplication.repository.weatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class favoriteViewModel @Inject constructor(private val repository: weatherDbRepository) :
    ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged()
                .collect { listOfFavs ->
                    if (listOfFavs.isEmpty()) {
                        Log.d("tag", "EMpty list")
                    } else {
                        _favList.value = listOfFavs
                        Log.d("TAG", ":${favList.value}")
                    }

                }
        }
    }
    fun insertFavorite(favorite: Favorite) = viewModelScope.launch { repository.insertFavorite(favorite) }
    fun updateFavorite(favorite: Favorite) = viewModelScope.launch { repository.updateFavorite(favorite) }
    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch { repository.deleteFavorite(favorite) }

}