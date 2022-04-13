package com.carfax.composeExample.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class AppColors(
	primary: Color,
	textPrimary: Color,
	textSecondary: Color,
	background: Color,
	error: Color,
	isLight: Boolean
) {
	var primary by mutableStateOf(primary)
		private set
	var textSecondary by mutableStateOf(textSecondary)
		private set
	var textPrimary by mutableStateOf(textPrimary)
		private set
	var error by mutableStateOf(error)
		private set
	var background by mutableStateOf(background)
		private set
	var isLight by mutableStateOf(isLight)
		internal set

	fun copy(
		primary: Color = this.primary,
		textPrimary: Color = this.textPrimary,
		textSecondary: Color = this.textSecondary,
		error: Color = this.error,
		background: Color = this.background,
		isLight: Boolean = this.isLight
	): AppColors = AppColors(
		primary,
		textPrimary,
		textSecondary,
		error,
		background,
		isLight
	)

	fun updateColorsFrom(other: AppColors) {
		primary = other.primary
		textPrimary = other.textPrimary
		textSecondary = other.textSecondary
		background = other.background
		error = other.error
	}
}

private val colorLightPrimary = Color(0xD20037FF)
private val colorLightTextPrimary = Color(0xFF121212)
private val colorLightTextSecondary = Color(0xFF6C727A)
private val colorLightBackground = Color(0xFFFFFFFF)
private val colorLightError = Color.Red
private val colorLightWarning = Color.Yellow
private val colorLightGood = Color.Green

private val colorDarkPrimary = Color(0xD20037FF)
private val colorDarkTextPrimary = Color(0xFFFAFAFA)
private val colorDarkTextSecondary = Color(0xFF6C727A)
private val colorDarkBackground = Color(0xFF121212)
private val colorDarkError = Color(0xFFD62222)
private val colorDarkWarning = Color.Yellow
private val colorDarkGood = Color.Green

fun lightColors(
	primary: Color = colorLightPrimary,
	textPrimary: Color = colorLightTextPrimary,
	textSecondary: Color = colorLightTextSecondary,
	background: Color = colorLightBackground,
	error: Color = colorLightError
): AppColors = AppColors(
	primary = primary,
	textPrimary = textPrimary,
	textSecondary = textSecondary,
	background = background,
	error = error,
	isLight = true
)

fun darkColors(
	primary: Color = colorDarkPrimary,
	textPrimary: Color = colorDarkTextPrimary,
	textSecondary: Color = colorDarkTextSecondary,
	background: Color = colorDarkBackground,
	error: Color = colorDarkError
): AppColors = AppColors(
	primary = primary,
	textPrimary = textPrimary,
	textSecondary = textSecondary,
	background = background,
	error = error,
	isLight = false
)

val LocalColors = staticCompositionLocalOf { darkColors() }