package com.shubhans.weatherforcastapplication.screens.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SettingViewModel  @Inject constructor(private val repository: weatherDbRepository):ViewModel(){
    val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList =_unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged()
                .collect{listofUnits->
                    if(listofUnits.isEmpty()){
                        Log.d("Tag","Empty list")
                    }else{
                        _unitList.value =listofUnits
                        Log.d("Tag","${unitList.value}")
                    }

                }
        }

    }
    fun insertUnits(unit: Unit) = viewModelScope.launch { repository.insertUnits(unit) }
    fun updateUnits(unit: Unit) = viewModelScope.launch { repository.updateUnits(unit) }
    fun deleteUnits(unit: Unit) = viewModelScope.launch { repository.deleteUnits(unit) }

}