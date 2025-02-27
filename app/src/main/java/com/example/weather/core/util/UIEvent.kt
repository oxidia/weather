package com.example.weather.core.util

sealed class UIEvent {
    data object PopBackStack: UIEvent()
    data class OnNavigate(val screen: Screen) : UIEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null,
    ): UIEvent()
}
