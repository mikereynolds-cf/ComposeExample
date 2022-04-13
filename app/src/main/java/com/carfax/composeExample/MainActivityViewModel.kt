package com.carfax.composeExample

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carfax.composeExample.`object`.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

	// Vehicle to hold
	internal val vehicle = mutableStateOf(Vehicle(2002, "Ford", "Ranger", 12000.9, "My Comments"))
	private var backupVehicle = Vehicle(2022, "Toyota", "Tacoma", 10.0, "My NEW CAR!!")

	internal val loading = mutableStateOf(false)

	internal fun swapVehicles() {
		viewModelScope.launch(Dispatchers.IO) {
			loading.value = true
		val newVehicle = backupVehicle
		backupVehicle = vehicle.value
		vehicle.value = newVehicle
		delay(2000)
		loading.value = false
		}
	}
}