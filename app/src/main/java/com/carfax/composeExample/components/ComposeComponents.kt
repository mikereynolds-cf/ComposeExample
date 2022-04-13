package com.carfax.composeExample.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carfax.composeExample.R
import com.carfax.composeExample.`object`.Vehicle
import com.carfax.composeExample.theme.AppTheme

@Composable
fun Ball(
	modifier: Modifier = Modifier,
	size: Dp = 70.dp,
	backgroundColor: Color = Color.Gray.copy(alpha = 0.8f),
) {
	AppTheme {
		Box(
			modifier = modifier
				.width(size)
				.height(size)
				.clipToBounds()
				.background(backgroundColor, CircleShape)
		)
	}
}

/**
 * The Vehicle Data List Item Composable.
 *
 * @param vehicleData The [Vehicle] to Display.
 */
@Composable
fun VehicleDataListItem(vehicleData: MutableState<Vehicle>) {
	Card(
		backgroundColor = Color.Gray,
		modifier = Modifier
			.fillMaxWidth()
			.padding(4.dp)
	) {
		Column(
			modifier = Modifier
				.padding(8.dp)
				.fillMaxWidth()
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(4.dp)
			) {
				BadgedBox(badge = { Badge(modifier = Modifier.wrapContentSize()) {
					Text(vehicleData.value.notifications.toString(), fontSize = 16.sp) } }) {
					Image(
						bitmap = ImageBitmap.imageResource(id = R.drawable.image_not_found),
						contentDescription = "Image not Found",
						modifier = Modifier
							.size(110.dp, 100.dp)
							.align(Center),
						contentScale = ContentScale.Fit
					)
				}
				Row(modifier = Modifier.fillMaxWidth()) {
					Column(modifier = Modifier.padding(8.dp)) {
						Text(
							text = "${vehicleData.value.year} ${vehicleData.value.make} ${vehicleData.value.model}",
							fontWeight = FontWeight.Bold,
							fontSize = 18.sp
						)
						StarRating(rating = remember { mutableStateOf(vehicleData.value.rating) })
					}
					Box(modifier = Modifier.aspectRatio(1f)) {
						Icon(
							Icons.Default.RestartAlt,
							contentDescription = "More Options",
							tint = AppTheme.colors.background,
							modifier = Modifier
								.align(CenterEnd)
						)
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun preview() {
	VehicleDataListItem(vehicleData = remember { mutableStateOf(Vehicle(2002, "Ford", "Ranger", 12000.9, "My Comments", 1, 3)) })
}

@Composable
fun StarRating(rating: MutableState<Int>) {
	Row {
		Star(color = if (1 <= rating.value) Color.Yellow else Color.White)
		Star(color = if (2 <= rating.value) Color.Yellow else Color.White)
		Star(color = if (3 <= rating.value) Color.Yellow else Color.White)
		Star(color = if (4 <= rating.value) Color.Yellow else Color.White)
		Star(color = if (5 <= rating.value) Color.Yellow else Color.White)
	}
}

@Composable
private fun Star(color: Color) {
	Icon(Icons.Default.Star,
		contentDescription = "Star",
		tint = color,
		modifier = Modifier
			.size(25.dp)
			.padding(2.dp)
		)
}