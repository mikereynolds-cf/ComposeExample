package com.carfax.composeExample.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.carfax.composeExample.Vehicle

@Composable
fun DashboardYear(vehicle: MutableState<Vehicle>) {
	BaseDashboardItem(title = "Year", value = vehicle.value.year.toString(), vehicle = vehicle)
}

@Composable
fun DashboardMake(vehicle: MutableState<Vehicle>) {
	BaseDashboardItem(title = "Make", value = vehicle.value.make, vehicle = vehicle)
}

@Composable
fun DashboardModel(vehicle: MutableState<Vehicle>) {
	BaseDashboardItem(title = "Model", value = vehicle.value.model, vehicle = vehicle)
}

@Composable
fun DashboardMiles(vehicle: MutableState<Vehicle>) {
	BaseDashboardItem(title = "Miles", value = vehicle.value.miles.toString(), vehicle = vehicle)
}

@Composable
fun DashboardComments(vehicle: MutableState<Vehicle>) {
	BaseDashboardItem(title = "Comments", value = vehicle.value.comments, vehicle = vehicle, dialogEditComments = true)
}

@Composable
private fun BaseDashboardItem(
	title: String,
	value: String,
	vehicle: MutableState<Vehicle>,
	dialogEditComments: Boolean = false
) {
	val isDialogOpen = rememberSaveable { mutableStateOf(false) }
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(50.dp)
			.padding(8.dp)
			.border(width = 1.dp, Color.Black)
			.clickable(enabled = dialogEditComments, onClick = { isDialogOpen.value = true })
	) {
		Text(
			text = title, fontWeight = FontWeight.Bold, modifier = Modifier
				.align(CenterVertically)
				.padding(8.dp)
		)
		Text(text = value, modifier = Modifier.align(CenterVertically))
		UpdateCommentsDialog(vehicle = vehicle, isDialogOpen = isDialogOpen)
	}
}

@Composable
private fun UpdateCommentsDialog(vehicle: MutableState<Vehicle>, isDialogOpen: MutableState<Boolean>) {
	if (isDialogOpen.value) {
		Dialog(onDismissRequest = { isDialogOpen.value = false }) {
			Column(modifier = Modifier
				.fillMaxWidth()
				.border(BorderStroke(1.dp, Color.Blue))
				.background(Color(0xFFFFFFFF))) {
				OutlinedTextField(
					modifier = Modifier.fillMaxWidth().padding(8.dp),
					value = vehicle.value.comments,
					onValueChange = { vehicle.value = vehicle.value.copy(comments = it) },
					label = { Text("Comments") })

				TextButton(onClick = { isDialogOpen.value = false }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
					Text(text = "Save")
				}
			}
		}
	}
}
