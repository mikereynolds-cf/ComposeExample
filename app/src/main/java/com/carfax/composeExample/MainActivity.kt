package com.carfax.composeExample

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carfax.composeExample.components.*
import com.carfax.composeExample.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

	private val viewModel: MainActivityViewModel by viewModels()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		lifecycleScope.launch(Dispatchers.IO) {
			viewModel.loading.value = true
			delay(4000)
			viewModel.loading.value = false
		}
		setContent {
			AppTheme {
				Column(modifier = Modifier.fillMaxSize(1f)) {
					if (viewModel.loading.value) {
						Box(modifier = Modifier.fillMaxSize()) {
							val infiniteTransition = rememberInfiniteTransition()
							val animationProgress by infiniteTransition.animateFloat(
								initialValue = 0f,
								targetValue = 1f,
								animationSpec = infiniteRepeatable(
									animation = tween(durationMillis = 800)
								)
							)
							Ball(
								modifier = Modifier
									.scale(animationProgress)
									.align(Alignment.Center)
									.alpha(1 - animationProgress)
							)
						}
					}
					else {
						TextButton(onClick = { viewModel.swapVehicles() }) {
							Text(text = "Switch Vehicles", color = AppTheme.colors.textPrimary)
						}
						VehicleDataListItem(vehicleData = viewModel.vehicle.value)
					}
				}
			}
		}
	}
}