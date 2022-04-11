package com.carfax.composeExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.carfax.composeExample.components.*

class MainActivity : AppCompatActivity() {

	private val viewModel: MainActivityViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MaterialTheme {
				Column(modifier = Modifier.fillMaxSize(1f)) {
					if (viewModel.loading.value) {
						Box(modifier = Modifier.fillMaxSize()) {
							CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
						}
					}
					else {
						TextButton(onClick = { viewModel.swapVehicles() }) {
							Text("Switch Vehicles")
						}
						viewModel.vehicle.let {
							DashboardYear(it)
							DashboardMake(it)
							DashboardModel(it)
							DashboardMiles(it)
							DashboardComments(it)
						}
					}
				}
			}
		}
	}
}