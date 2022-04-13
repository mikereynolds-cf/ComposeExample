package com.carfax.composeExample.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
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
fun VehicleDataListItem(vehicleData: Vehicle) {
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
				BadgedBox(badge = { Badge(modifier = Modifier.wrapContentSize()) { Text("9", fontSize = 16.sp) } }) {
					Image(
						bitmap = ImageBitmap.imageResource(id = R.drawable.image_not_found),
						contentDescription = null,
						modifier = Modifier
							.size(110.dp, 100.dp)
							.align(Center),
						contentScale = ContentScale.Fit
					)
				}
				Row(modifier = Modifier.fillMaxWidth()) {
					Column(modifier = Modifier.padding(8.dp)) {
						Text(
							text = "${vehicleData.year} ${vehicleData.make} ${vehicleData.model}",
							fontWeight = FontWeight.Bold,
							fontSize = 18.sp
						)
					}
					Box(modifier = Modifier.aspectRatio(1f)) {
						Icon(
							Icons.Default.MoreVert,
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
